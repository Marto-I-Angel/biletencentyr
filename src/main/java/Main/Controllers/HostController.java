package Main.Controllers;

import entities.Event;
import entities.Host;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.TicketView;
import notifications.CheckForSoldTickets;
import notifications.CheckForUpcomingEvent;
import services.EventService;
import services.HostService;
import services.SessionService;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HostController implements Initializable {

    public TableView<Event> event_table;
    public TableColumn<Event,String> col_event_name;
    public TableColumn<Event,String> col_Type_event;
    public TableColumn<Event,String> col_event_date_begin;
    public TableColumn<Event,String> col_event_date_end;
    public TableColumn<Event,String> col_event_status;
    public Label lb_username;
    public Label lbl_notification;
    public Button bt_viewdistributors;
    public TextField txt_username;
    public TextField txt_pass;
    public TextField txt_newPass;
    public TextField txt_newPass2;
    public ListView<String> listView_sold_tickets;
    public Label lbl_error;

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

    private int tempNumNotif;

    public void add_new_event() throws IOException,RuntimeException {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Add");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/eventView.fxml")));
        Parent root = loader.load();
        Scene scene1= new Scene(root);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

        refresh_event_table();
    }
    public void editSelectedEvent() throws IOException,RuntimeException {
        if(event_table.getSelectionModel().getSelectedIndex()>=0) {
            Stage popupwindow = new Stage();
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle("Add");
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/eventView.fxml")));
            Parent root = loader.load();
            EventViewController adder = loader.getController();
            EventService service = new EventService();
            Event event1 = service.loadEvent(event_table.getSelectionModel().getSelectedItem().getEventId());
            adder.setEvent(event1);
            Scene scene1 = new Scene(root);
            popupwindow.setScene(scene1);
            popupwindow.showAndWait();

            refresh_event_table();

        }
    }
    public void refresh_event_table() {
        EventService eventService = new EventService();
        List<Event> all = eventService.findAll();
        ObservableList<Event> events = FXCollections.observableArrayList();
        events.setAll(all);
        event_table.setItems(events);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        executor.shutdown();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login Screen");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/login.fxml")));
        SessionService.logout();
        Scene scene = new Scene (root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_event_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Type_event.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        col_event_date_begin.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        col_event_date_end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        col_event_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        refresh_event_table();

        String username = SessionService.getHost().getUser().getUsername();
        lb_username.setText(username);
        txt_username.setText(username);

        //notification setup
        //TODO: change the soldTicketsNum from 0 to the number of tickets since the last check!
        Runnable r = new CheckForSoldTickets(0,this);
        Runnable r1 = new CheckForUpcomingEvent(this);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        executor.scheduleAtFixedRate(r,0,5, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(r1,0,1,TimeUnit.HOURS);
    }

    public void delete_selected_event() {
        EventService eventService=new EventService();
        eventService.delete(event_table.getSelectionModel().getSelectedItem().getEventId());
        refresh_event_table();
    }

    public void updateNotification(int num,List<TicketView> list) {
        if(num>0 ) {
            if(num != tempNumNotif) {
                tempNumNotif = num;
                lbl_notification.setText(num + " TICKETS HAVE BEEN SOLD!!");
                for(TicketView x : list) {
                    listView_sold_tickets.getItems().add(x.toString());
                }
            }
        }
        else {
            lbl_notification.setText("No new notifications");
            listView_sold_tickets.getItems().clear();
        }
    }

    public void viewDistributors() throws IOException {
        Stage popUpWindow=new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Queries");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/queries.fxml")));
        Parent root = loader.load();
        Scene scene1= new Scene(root);
        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();
    }

    public void btn_mark_seen() {
        if(!lbl_notification.getText().equals("No new notifications"))
        SessionService.setNotifNumber(Integer.parseInt(lbl_notification.getText().
                substring(0,lbl_notification.getText().indexOf(' '))));
        listView_sold_tickets.getItems().clear();
    }

    public void updateAccount() {
        Host host = SessionService.getHost();
        if(txt_pass.getText().equals(host.getUser().getPassword()))
        {
            if(!txt_newPass.getText().isEmpty() && txt_newPass.getText().length()>=5)
            {
                if(txt_newPass.getText().equals(txt_newPass2.getText())) {
                    User user = host.getUser();
                    user.setPassword(txt_newPass.getText());
                    user.setUsername(txt_username.getText());
                    txt_newPass.setText("");
                    txt_newPass2.setText("");
                    txt_username.setText(host.getUser().getUsername());
                    lbl_error.setText("");

                    HostService hostService = new HostService();
                    UserService userService = new UserService();
                    userService.update(user);
                    hostService.update(host);
                }
                else lbl_error.setText("The new passwords dont match!");
            }
            else lbl_error.setText("The new Password does not meet the requirements!");
        }
        else{
            lbl_error.setText("Wrong password");
        }

    }

    public void upcomingEventNotif(List<Event> upcomingEvents) {
        for(Event x : upcomingEvents)
        {
            listView_sold_tickets.getItems().add("The event "+x.getName()+ " will occur in under a week\n and has unsold tickets!");
        }
    }
}

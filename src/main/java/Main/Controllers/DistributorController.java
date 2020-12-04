package Main.Controllers;

import entities.Distribution;
import entities.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import notifications.CheckForNewEvent;
import models.EventView;
import models.TicketView;
import services.DistributionService;
import services.EventService;
import services.SessionService;
import services.TicketService;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DistributorController implements Initializable {

    //Event table (tab1)
    public TableView<EventView> event_table;
    public TableColumn<EventView,String> col_event_name;
    public TableColumn<EventView,String> col_Type_event;
    public TableColumn<EventView,String> col_event_date_begin;
    public TableColumn<EventView,String> col_event_date_end;
    public TableColumn<EventView,String> col_event_status;
    public TableColumn<EventView,String> col_fee;
    public TableColumn<EventView,String> col_tickets;
    public Label lb_username;
    public Label label_rating;

    //Ticket table (tab2)
    public TableView<TicketView> ticket_table;
    public TableColumn<TicketView,String> col_event_name_2;
    public TableColumn<TicketView,String> col_type_seats;
    public TableColumn<TicketView,Integer> col_number_of_seats;
    public TableColumn<TicketView,String> col_date_bought;
    public TableColumn<TicketView,Float> col_total_value;
    public TableColumn<TicketView,String> col_person_names;
    public TableColumn<TicketView,String> col_payment_type;
    public Label lbl_notification_msg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_event_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Type_event.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_event_date_begin.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        col_event_date_end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        col_event_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_fee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        col_tickets.setCellValueFactory(new PropertyValueFactory<>("tickets"));

        col_event_name_2.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        col_type_seats.setCellValueFactory(new PropertyValueFactory<>("typeSeats"));
        col_number_of_seats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        col_date_bought.setCellValueFactory(new PropertyValueFactory<>("dateBought"));
        col_total_value.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        col_person_names.setCellValueFactory(new PropertyValueFactory<>("personNames"));
        col_payment_type.setCellValueFactory(new PropertyValueFactory<>("typePayment"));


        refresh_event_table();
        refresh_ticket_table();

        //notification setup
        Runnable r = new CheckForNewEvent(this);
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(r,0,5, TimeUnit.SECONDS);

    }
    public void refresh_event_table() {
        EventService eventService = new EventService();
        List<Event> all;
        all = eventService.findByDistributorId(SessionService.getDistributor().getDistributorId(),true);

        ObservableList<EventView> events = eventService.toEventView(all,SessionService.getDistributor().getDistributorId());
        event_table.setItems(events);
    }
    public void refresh_ticket_table() {
        TicketService ticketService = new TicketService();
        List<TicketView> all = ticketService.getTicketViews(SessionService.getDistributor().getDistributorId());
        ObservableList<TicketView> tickets = FXCollections.observableArrayList();
        tickets.addAll(all);
        ticket_table.setItems(tickets);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login Screen");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/login.fxml")));
        SessionService.logout();
        Scene scene = new Scene (root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void sell_ticket() throws IOException {
        Stage popUpWindow=new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Create a ticket");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/ticketEditor.fxml")));
        Parent root = loader.load();
        TicketEditorController adder = loader.getController();
        EventService eventService = new EventService();
        adder.setEvent(eventService.findById(this.event_table.getSelectionModel().getSelectedItem().getEventId()));
        Scene scene1= new Scene(root);
        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();

        refresh_event_table();

    }

    public void notificationUpdate(int num) {
        if(num > 0) {
            lbl_notification_msg.setTextFill(Color.BLUE);
            lbl_notification_msg.setText("THERE ARE " + num + " NOTIFICATION/S FOR YOU!");
        }
        else
        {
            lbl_notification_msg.setText("There are no new notifications :(");
        }
    }

    public void show_notifications() {
        EventService eventService = new EventService();
        DistributionService distributionService = new DistributionService();
        List<Event> list = eventService.findByDistributorId(SessionService.getDistributor().getDistributorId(),false);
        for(Event x: list) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("New Event");
            alert.setContentText("You have been chosen to distribute tickets for the selected event:" +x.getName());
            ButtonType okButton = new ButtonType("Accept", ButtonBar.ButtonData.YES);
            ButtonType cancelButton = new ButtonType("Decline", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);
            Optional<ButtonType> result = alert.showAndWait();
            Distribution distribution =  distributionService.findDistribution(SessionService.getDistributor().getDistributorId(),x.getEventId());

            if (result.get() == okButton) {
                distribution.setAccepted(true);
                distributionService.update(distribution);
            }else distributionService.delete(distribution.getId());
        }
    }
}

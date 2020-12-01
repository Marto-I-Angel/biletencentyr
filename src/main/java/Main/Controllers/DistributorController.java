package Main.Controllers;

import entities.Event;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.EventView;
import services.EventService;
import services.SessionService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

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
    public TableView ticket_table;
    public TableColumn col_event_name_2;
    public TableColumn col_type_seats;
    public TableColumn col_number_of_seats;
    public TableColumn col_date_bought;
    public TableColumn col_total_value;
    public TableColumn col_person_names;
    public TableColumn col_payment_type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_event_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Type_event.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_event_date_begin.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        col_event_date_end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        col_event_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_fee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        col_tickets.setCellValueFactory(new PropertyValueFactory<>("tickets"));

        refresh_event_table();
    }

    public void refresh_event_table() {
        EventService eventService = new EventService();
        List<Event> all;
        all = eventService.findByDistributorId(SessionService.getDistributor().getDistributorId());

        ObservableList<EventView> events = eventService.toEventView(all,SessionService.getDistributor().getDistributorId());
        event_table.setItems(events);
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

    public void check_sold_tickets(ActionEvent actionEvent) {

    }

    public void sell_ticket(ActionEvent actionEvent) throws IOException {
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

    public void refresh_ticket_table(MouseEvent mouseEvent) {

    }
}

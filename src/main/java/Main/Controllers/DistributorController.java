package Main.Controllers;

import entities.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.DistributionService;
import services.EventService;
import services.SessionService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DistributorController implements Initializable {

    public TableView<Event> event_table;
    public TableColumn<Event,String> col_event_name;
    public TableColumn<Event,String> col_Type_event;
    public TableColumn<Event,String> col_event_date_begin;
    public TableColumn<Event,String> col_event_date_end;
    public TableColumn<Event,String> col_event_status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_event_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Type_event.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        col_event_date_begin.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        col_event_date_end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        col_event_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        refresh_event_table();
    }

    public void refresh_event_table() {
        EventService eventService = new EventService();
        List<Event> all = eventService.findAll();
        //  fix logic here to get events that belong only to current distributor

        DistributionService distributionService = new DistributionService();
        all = distributionService.findByDistributorId(SessionService.getDistributor().getDistributorId());  //gets currently logged in distributor id


        ObservableList<Event> events = FXCollections.observableArrayList();
        events.setAll(all);
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
}

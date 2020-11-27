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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.EventService;
import services.SessionService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HostController implements Initializable {

    public TableView<Event> event_table;
    public TableColumn<Event,String> col_event_name;
    public TableColumn<Event,String> col_Type_event;
    public TableColumn<Event,String> col_event_date_begin;
    public TableColumn<Event,String> col_event_date_end;
    public TableColumn<Event,String> col_event_status;
    public Label lb_username;

    public void add_new_event() throws IOException,RuntimeException {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Add");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/eventView.fxml")));
        Parent root = loader.load();
        Scene scene1= new Scene(root);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
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
    }

    public void delete_selected_event(MouseEvent mouseEvent) {
        EventService eventService=new EventService();
        eventService.delete(event_table.getSelectionModel().getSelectedItem().getEventId());
        refresh_event_table();
    }
}

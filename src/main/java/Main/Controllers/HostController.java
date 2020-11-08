package Main.Controllers;

import entities.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.SessionService;

import java.io.IOException;
import java.util.Objects;

public class HostController {

    public TableView<Event> event_table;
    public TableColumn<Event,String> col_event_name;
    public TableColumn<Event,String> col_event_date;
    public Label lb_username;

    public void add_new_event() throws IOException {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Add");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/eventView.fxml")));
        Parent root = loader.load();
        Scene scene1= new Scene(root);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }
    public void editSelectedEvent() throws IOException {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Add");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/eventView.fxml")));
        EventViewController adder = loader.getController();
        /*
            Load the event from the DB and add the values to the opening window.
        */
        Parent root = loader.load();
        Scene scene1= new Scene(root);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }
    public void refresh_event_table() {
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

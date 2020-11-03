package Main.Controllers;

import entities.User;
import entities.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.SessionService;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    public Label username_label;
    @FXML
    public TableView<User> table= new TableView<>();
    @FXML
    public TableColumn<User,Integer> idCol;
    @FXML
    public TableColumn<User, String> UsernameCol;
    @FXML
    public TableColumn<User, String> PasswordCol;
    @FXML
    public TableColumn<User,UserRole> UserRoleCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    public void refresh_db() {
        refresh();
    }

    public void add_to_db() throws IOException {
        Stage popUpWindow=new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Add");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/add.fxml")));
        Parent root = loader.load();
        AddController adder = loader.getController();
        adder.roles.setItems(FXCollections.observableArrayList(
                new UserRole("host"),
                new UserRole("distributor"))
        );

        Scene scene1= new Scene(root, 300, 350);
        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();
        refresh();
    }

    public void delete_db() throws RuntimeException {
        User person = table.getSelectionModel().getSelectedItem();
        UserService userService = new UserService();
        userService.delete(person.getUserId());
        refresh();
    }

    private void refresh()
    {
        UserService userService = new UserService();
        List<User> list1;
        list1 = userService.findAll();
        ObservableList<User> obs = FXCollections.observableArrayList();
        idCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        UsernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        PasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        UserRoleCol.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        obs.addAll(list1);
        table.setItems(obs);
        table.setVisible(true);
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

package Main.Controllers;

import entities.Host;
import entities.User;
import entities.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.UserService;
import util.HibernateUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    public Label username_label;
    @FXML
    public TableView<User> table= new TableView<User>();
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

    public void refresh_db(MouseEvent mouseEvent) {
        refresh();
    }

    public void add_to_db(MouseEvent mouseEvent) throws IOException {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Add");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/add.fxml")));
        Parent root = loader.load();
        AddController adder = loader.getController();
        adder.roles.setItems(FXCollections.observableArrayList(
                new UserRole("host"),
                new UserRole("distributor"))
        );

        Scene scene1= new Scene(root, 300, 350);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
        refresh();
    }

    public void delete_db(MouseEvent mouseEvent) throws RuntimeException {
        User person = table.getSelectionModel().getSelectedItem();
        UserService userService = new UserService();
        userService.delete(person.getUserId());
        refresh();
    }

    private void refresh()
    {
        UserService userService = new UserService();
        List<User> list1 = new ArrayList<>();
        list1 = userService.findAll();
        ObservableList<User> obs = FXCollections.observableArrayList();
        idCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        UsernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        PasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        UserRoleCol.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        obs.addAll(list1);
        table.setItems(obs);
        table.setEditable(true);
        table.setVisible(true);
    }
}

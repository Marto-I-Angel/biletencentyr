package Main.Controllers;


import Main.Main;
import entities.User;
import entities.UserRole;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.UserService;
import util.HibernateUtil;

import javax.management.relation.Role;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login_controller implements Initializable  {
    public TextField usernameField;
    public Button login;
    public PasswordField passwordField;
    public Label label;

    public void log_in_function(ActionEvent actionEvent) throws IOException {



        if(actionEvent.getTarget() == login) {
            String password = passwordField.getText();
            String username = usernameField.getText();

            User user = new User(username,password);

            if (password.length() < 5 || username.length() < 5) {
                label.setText("Passwords and usernames are longer than 5 symbols");
                passwordField.clear();
            } else {
                label.setText("Loading...");

//                System.out.println(passwordField.getText());
//                System.out.println(usernameField.getText());

//                String loginRole;
//                UserService userService1 = new UserService();
//                List<User> list = userService1.findAll();
//                for (User iter:list) {
//                    if(iter.equals(user)){
//                        loginRole = iter.getUserRole();
//                    }
//                }
                UserService userService = new UserService();
                List<User> list = userService.findAll();

                boolean itsadmin = false;
                boolean itshost = false;
                boolean itsdistributor = false;
                for (User iter:list) {
                    if(iter.equals(user)){
                        if(iter.checkRole("admin"))
                        {
                            itsadmin = true;
                            break;
                        }
                        if(iter.checkRole("host"))
                        {
                            itshost = true;
                            break;
                        }
                        if(iter.checkRole("distributor"))
                        {
                            itsdistributor = true;
                            break;
                        }
                    }
                }
                if(!itsadmin && !itshost && !itsdistributor){
                    label.setText("Incorrect username or password!");
                }

                if(itsadmin)
                {

                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/admin.fxml")));
                    Parent root = loader.load();
                    AdminController admin = loader.getController();
                    admin.username_label.setText(username);
                    Scene scene = new Scene (root);
                    stage.setTitle("Admin");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                }
                if(itshost)
                {
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/host.fxml")));
                    Parent root = loader.load();
                    HostController host = loader.getController();
                    host.lb_username.setText(username);
                    Scene scene = new Scene (root);
                    stage.setTitle("Host-control");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.setText("");
        passwordField.setText("");
        label.setText("");
    }
}

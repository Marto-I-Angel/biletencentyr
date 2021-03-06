package Main.Controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.DistributorService;
import services.HostService;
import services.SessionService;
import services.UserService;
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
                HostService hostService = new HostService();
                DistributorService distributorService = new DistributorService();
                UserService userService = new UserService();
                List<User> list = userService.findAll();

                boolean itsAdmin = false;
                boolean itsHost = false;
                boolean itsDistributor = false;
                for (User iter:list) {
                    if(iter.equals(user)){
                        if(iter.checkRole("admin"))
                        {
                            itsAdmin = true;
                            break;
                        }
                        if(iter.checkRole("host"))
                        {
                            itsHost = true;
                            SessionService.setHost(hostService.getByUsername(iter.getUsername()));
                            break;
                        }
                        if(iter.checkRole("distributor"))
                        {
                            itsDistributor = true;
                            SessionService.setDistributor(distributorService.getByUsername(iter.getUsername()));
                            break;
                        }
                    }
                }
                if(!itsAdmin && !itsHost && !itsDistributor){
                    label.setText("Incorrect username or password!");
                }

                if(itsAdmin)
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
                if(itsHost)
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
                if(itsDistributor)
                {
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/distributor.fxml")));
                    Parent root = loader.load();
                    Scene scene = new Scene (root);
                    stage.setTitle("Distributor Scene");
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

package Main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
            if (password.length() < 5 || username.length() < 5) {
                label.setText("Passwords and usernames are longer than 5 symbols");
                passwordField.clear();
            } else {
                label.setText("Loading...");
                if(usernameField.getText().equals("admin") && passwordField.getText().equals("admin")
//                        verifyAcc(usernameField.getText(),passwordField.getText())
                )
                {
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/admin.fxml")));
                    Parent root = loader.load();
                    AdminController admin = loader.getController();
                    admin.username_label.setText("<username>");
                    Scene scene = new Scene (root);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }
//    // Check for username and password in the database
//    private boolean verifyAcc(String name, String pass)
//    {
//        Tuk si pishesh funkciqta, Marto
//        if(true)
//        return true;
//        return false;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.setText("");
        passwordField.setText("");
        label.setText("");
    }
}

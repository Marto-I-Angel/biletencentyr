package Main;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable  {
    public TextField usernameField;
    public Button login;
    public PasswordField passwordField;
    public Label label;

    public void log_in_function(ActionEvent actionEvent) {
        if(actionEvent.getTarget() == login) {
            String password = passwordField.getText();
            String username = usernameField.getText();
            if (password.length() < 6 || username.length() < 6) {
                label.setText("Passwords and usernames are longer than 6 symbols");
                passwordField.clear();
            } else {
                label.setText("Loading...");
                System.out.println(passwordField.getText());
                System.out.println(usernameField.getText());
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

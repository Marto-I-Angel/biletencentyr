package Main.Controllers;

import entities.Distributor;
import entities.Host;
import entities.User;
import entities.UserRole;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import services.DistributorService;
import services.HostService;
import services.UserService;

import java.util.List;

import services.DatabaseLoader;

public class AddController {

    public TextField username;
    public ComboBox<UserRole> roles;
    public Button add_btn;
    public PasswordField pass;
    public PasswordField confirm_pass;
    public Label error_lab;

    @FXML
    private void addNewAcc() {
        if (!username.getText().equals(""))
            if (pass.getText().equals(confirm_pass.getText()) && pass.getText().length() >= 5) {
                if (!roles.getValue().toString().equals("")) {
                    create(username.getText(), pass.getText(), roles.getValue().toString());
                } else error_lab.setText("No role detected");
            } else {
                error_lab.setText("Passwords dont match");
            }
        username.clear();
        pass.clear();
        confirm_pass.clear();

    }

    public void create(String username, String password, String role_name) {
        if (findUser(username)) {
            error_lab.setTextFill(Color.RED);
            error_lab.setText("User already exists");
        } else {
            UserService userService = new UserService();
            UserRole role = DatabaseLoader.getRole(role_name);
            User user = new User(username, password, role);
            userService.persist(user);
            if (role_name.equals("host")) {
                HostService hostService = new HostService();
                Host host = new Host(user);
                hostService.persist(host);
            }
            if (role_name.equals("distributor")) {
                DistributorService distributorService = new DistributorService();
                Distributor distributor = new Distributor(user);
                distributorService.persist(distributor);
            }
            error_lab.setTextFill(Color.GREEN);
            error_lab.setText("Success!");
        }
    }

    private boolean findUser(String username) {
        UserService userService = new UserService();
        List<User> list = userService.findAll();
        for (User iter : list) {
            if (iter.getUsername().equals(username))
                return true;
        }
        return false;

    }
}

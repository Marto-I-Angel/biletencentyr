package Main.Controllers;

import entities.Host;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.HostService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public Label username_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        HostService hostService = new HostService();
//        List<Host> list = new ArrayList<>();
//        list = hostService.findAll();
    }

    public void refresh_db(MouseEvent mouseEvent) {
    }
}

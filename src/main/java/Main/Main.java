package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.DatabaseLoader;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        DatabaseLoader.createUserRoles();
        stage.setTitle("Login Screen");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/login.fxml")));
        Scene scene = new Scene (root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

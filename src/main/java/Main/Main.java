package Main;

import entities.User;
import entities.UserRole;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.UserService;
import util.HibernateUtil;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        UserRole role_admin = new UserRole("admin");
        UserRole role_host = new UserRole("host");
        UserRole role_distributor = new UserRole("distributor");
//        session.save(role_admin);
//        session.save(role_host);
//        session.save(role_distributor);
        User user = new User("admin","admin",role_admin);
        //session.save(user);
        User user1 = new User("host","host",role_host);
        //session.save(user1);
        tx.commit();


        stage.setTitle("Login Screen");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/login.fxml")));
        Scene scene = new Scene (root, 350, 500);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

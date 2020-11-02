package Main.Controllers;

import entities.Distributor;
import entities.Host;
import entities.User;
import entities.UserRole;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class AddController {

    public TextField username;
    public ComboBox roles;
    public Button add_btn;
    public PasswordField pass;
    public PasswordField confirm_pass;
    public Label error_lab;

    @FXML
    private void addNewAcc()
    {
        if(!username.getText().equals(""))
        if(pass.getText().equals(confirm_pass.getText()) && pass.getText().length()>=5) {
            if (roles.getValue().toString().equals("host"))
            {
                create_host(username.getText(), pass.getText());
            }
            else if(roles.getValue().toString().equals("distributor"))
            {
                create_distributor(username.getText(),pass.getText());
            }
            else error_lab.setText("No role detected");
        }
        else
        {
            error_lab.setText("Passwords dont match");
        }
        username.clear();
        pass.clear();
        confirm_pass.clear();

    }
    public void create_host(String username,String password) {

        UserRole role = new UserRole("host");
        User user = new User(username, password, role);
        Host host = new Host(user);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(role);
        session.save(user);
        session.save(host);

        tx.commit();
        error_lab.setTextFill(Color.GREEN);
        error_lab.setText("Success!");
    }
    public void create_distributor(String username, String password) {

            UserRole role = new UserRole("distributor");
            User user = new User(username, password, role);
            Distributor distributor = new Distributor(user);

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            session.save(role);
            session.save(user);
            session.save(distributor);

            tx.commit();
            error_lab.setTextFill(Color.GREEN);
            error_lab.setText("Success!");

    }
}

package services;

import entities.User;
import entities.UserRole;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class DatabaseLoader {
    public static UserRole role_admin = new UserRole("admin");
    public static UserRole role_host = new UserRole("host");
    public static UserRole role_distributor = new UserRole("distributor");

    public static UserRole getRole(String role_name) {
        switch(role_name)
        {
            case "admin": return role_admin;
            case "host": return role_host;
            case "distributor": return role_distributor;
            default:
                throw new IllegalStateException("Unexpected value: " + role_name);
        }
    }
    public static void createUserRoles()
    {

        Session session = HibernateUtil.getSessionFactory().openSession();


        UserRoleService service = new UserRoleService();
        Transaction tx = session.beginTransaction();

        List<UserRole> a = service.findAll();
        if(a.isEmpty()){
            session.saveOrUpdate(DatabaseLoader.role_admin);
            session.saveOrUpdate(DatabaseLoader.role_host);
            session.saveOrUpdate(DatabaseLoader.role_distributor);
            User user = new User("admin", "admin", role_admin);
            session.save(user);

        }
        else {
            role_admin = session.load(UserRole.class,1);
            role_host = session.load(UserRole.class,2);
            role_distributor = session.load(UserRole.class,3);
        }
        tx.commit();
    }
}

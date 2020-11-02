package services;

import entities.User;
import entities.UserRole;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class DatabaseLoader {
    public final static UserRole role_admin = new UserRole("admin");
    public final static UserRole role_host = new UserRole("host");
    public final static UserRole role_distributor = new UserRole("distributor");

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
        List<UserRole> a = service.findAll();
        if(a.isEmpty()){
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(DatabaseLoader.role_admin);
            session.saveOrUpdate(DatabaseLoader.role_host);
            session.saveOrUpdate(DatabaseLoader.role_distributor);
            User user = new User("admin", "admin", DatabaseLoader.role_admin);
            session.saveOrUpdate(user);
            tx.commit();
        }
    }
}

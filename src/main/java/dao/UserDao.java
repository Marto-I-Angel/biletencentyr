package dao;

import dao.Interfaces.UserDaoInterface;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import services.DistributorService;
import services.HostService;
import services.UserService;
import util.HibernateUtil;

import java.util.List;

public class UserDao implements UserDaoInterface<User> {
    private Session currentSession;

    private Transaction currentTransaction;

    public UserDao(){

    }

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

//    private static SessionFactory getSessionFactory() {
//        Configuration configuration = new Configuration().configure();
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties());
//        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
//        return sessionFactory;
//    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(User entity) {
        getCurrentSession().save(entity);
    }

    public void update(User entity) {
        getCurrentSession().update(entity);
    }

    public User findById(int id) {
        return getCurrentSession().get(User.class, id);
    }
    public void delete(User entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return (List<User>) getCurrentSession().createQuery("from User").list();
    }

    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }
}

package dao;

import dao.interfaces.DaoInterface;
import entities.Seats;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class SeatsDao implements DaoInterface<Seats> {
    private Session currentSession;

    private Transaction currentTransaction;

    public SeatsDao(){

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

    public void persist(Seats entity) {
        getCurrentSession().save(entity);
    }

    public void update(Seats entity) {
        getCurrentSession().update(entity);
    }

    public Seats findById(int id) {
        return getCurrentSession().get(Seats.class, id);
    }
    public void delete(Seats entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Seats> findAll() {
        return (List<Seats>) getCurrentSession().createQuery("from User").list();
    }

    public void deleteAll() {
        List<Seats> entityList = findAll();
        for (Seats entity : entityList) {
            delete(entity);
        }
    }
}

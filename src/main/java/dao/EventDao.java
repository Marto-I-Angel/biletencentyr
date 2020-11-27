package dao;

import dao.interfaces.DaoInterface;
import entities.Distribution;
import entities.Distributor;
import entities.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.DistributionService;
import util.HibernateUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EventDao implements DaoInterface<Event> {
    private Session currentSession;

    private Transaction currentTransaction;

    public EventDao(){

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

    public void persist(Event entity) {
        getCurrentSession().save(entity);
    }

    public void update(Event entity) {
        getCurrentSession().update(entity);
    }

    public Event findById(int id) {
        return getCurrentSession().get(Event.class, id);
    }

    public void delete(Event entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Event> findAll() {
        return (List<Event>) getCurrentSession().createQuery("from Event").list();
    }

    public void deleteAll() {
        List<Event> entityList = findAll();
        for (Event entity : entityList) {
            delete(entity);
        }
    }
}
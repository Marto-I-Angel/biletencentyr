package dao;

import dao.interfaces.DaoInterface;
import entities.Distribution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class DistributionDao implements DaoInterface<Distribution> {
    private Session currentSession;

    private Transaction currentTransaction;

    public DistributionDao(){

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

    public void persist(Distribution entity) {
        getCurrentSession().save(entity);
    }

    public void update(Distribution entity) {
        getCurrentSession().update(entity);
    }

    public Distribution findById(int id) {
        return getCurrentSession().get(Distribution.class, id);
    }

    public void delete(Distribution entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Distribution> findAll() {
        return (List<Distribution>) getCurrentSession().createQuery("from Distribution").list();
    }

    @Override
    public void deleteAll() {

    }

    public void deleteAll(int eventId) {
        List<Distribution> entityList = findAll();
        for (Distribution entity : entityList) {
            if(entity.getEvent().getEventId() == eventId)
                delete(entity);
        }
    }
}
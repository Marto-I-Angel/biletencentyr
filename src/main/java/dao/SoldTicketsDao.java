package dao;

import dao.interfaces.DaoInterface;
import entities.SoldTickets;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class SoldTicketsDao implements DaoInterface<SoldTickets> {
    private Session currentSession;

    private Transaction currentTransaction;

    public SoldTicketsDao(){

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

    public void persist(SoldTickets entity) {
        getCurrentSession().save(entity);
    }

    public void saveOrUpdate(SoldTickets entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void update(SoldTickets entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public SoldTickets findById(int id) {
        return null;
    }

    public void delete(SoldTickets entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<SoldTickets> findAll() {
        return (List<SoldTickets>) getCurrentSession().createQuery("from SoldTickets").list();
    }

    @Override
    public void deleteAll() {
        
    }
}
package dao;

import dao.Interfaces.HostDaoInterface;
import entities.Host;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class HostDao implements HostDaoInterface<Host> {
    private Session currentSession;

    private Transaction currentTransaction;

    public HostDao(){

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

    public void persist(Host entity) {
        getCurrentSession().save(entity);
    }

    public void update(Host entity) {
        getCurrentSession().update(entity);
    }

    public Host findById(int id) {
        Host Host = (Host) getCurrentSession().get(Host.class, id);
        return Host;
    }

    public void delete(Host entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Host> findAll() {
        List<Host> Hosts = (List<Host>) getCurrentSession().createQuery("from Host").list();
        return Hosts;
    }

    public void deleteAll() {
        List<Host> entityList = findAll();
        for (Host entity : entityList) {
            delete(entity);
        }
    }
}

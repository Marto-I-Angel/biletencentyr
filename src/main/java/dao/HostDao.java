package dao;

import dao.interfaces.DaoInterface;
import entities.Host;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.HostService;
import util.HibernateUtil;

import java.util.List;

public class HostDao implements DaoInterface<Host> {
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
        return getCurrentSession().get(Host.class, id);
    }

    public void delete(Host entity) {
        getCurrentSession().delete(entity);
    }

    public Host getByUsername(String username)
    {
        return getCurrentSession().createNativeQuery("SELECT * FROM host JOIN user on user.userId = host.userId WHERE username = '" + username + "';", Host.class).getSingleResult();

    }
    @SuppressWarnings("unchecked")
    public List<Host> findAll() {
        return (List<Host>) getCurrentSession().createQuery("from Host").list();
    }

    public void deleteAll() {
        List<Host> entityList = findAll();
        for (Host entity : entityList) {
            delete(entity);
        }
    }
}

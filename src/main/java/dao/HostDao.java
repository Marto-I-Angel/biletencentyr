package dao;

import dao.interfaces.CustomerDaoInterface;
import dao.interfaces.DaoInterface;
import entities.Host;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.HostService;
import util.HibernateUtil;

import java.util.List;

public class HostDao implements DaoInterface<Host>, CustomerDaoInterface<Host> {
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
    public Host findByUser(User user) {
        HostService hostService = new HostService();
        List<Host> hosts = hostService.findAll();
        int id1=0;
        for (Host hostit:hosts) {
            if(hostit.getUser().equals(user)){
                id1=hostit.getHostId();
                break;
            }
        }
        return getCurrentSession().get(Host.class, id1);
    }

    public void delete(Host entity) {
        getCurrentSession().delete(entity);
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

package dao;

import dao.interfaces.CustomerDaoInterface;
import dao.interfaces.DaoInterface;
import entities.Distributor;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.DistributorService;
import util.HibernateUtil;

import java.util.List;

public class DistributorDao implements DaoInterface<Distributor>, CustomerDaoInterface<Distributor> {
    private Session currentSession;

    private Transaction currentTransaction;

    public DistributorDao(){

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

    public void persist(Distributor entity) {
        getCurrentSession().save(entity);
    }

    public void update(Distributor entity) {
        getCurrentSession().update(entity);
    }

    public Distributor findById(int id) {
        return getCurrentSession().get(Distributor.class, id);
    }
    public Distributor findByUser(User user) {
        DistributorService distributorService = new DistributorService();
        List<Distributor> distributors = distributorService.findAll();
        int id1=0;
        for (Distributor distributorit:distributors) {
            if(distributorit.getUser().equals(user)){
                id1=distributorit.getDistributorId();
                break;
            }
        }
        return getCurrentSession().get(Distributor.class, id1);
    }

    public void delete(Distributor entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Distributor> findAll() {
        return (List<Distributor>) getCurrentSession().createQuery("from Distributor").list();
    }

    public void deleteAll() {
        List<Distributor> entityList = findAll();
        for (Distributor entity : entityList) {
            delete(entity);
        }
    }
}

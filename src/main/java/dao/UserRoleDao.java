package dao;

import entities.UserRole;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class UserRoleDao {
    private Session currentSession;

    private Transaction currentTransaction;

    public UserRoleDao(){

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

    public void persist(UserRole entity) {
        getCurrentSession().save(entity);
    }

    public void update(UserRole entity) {
        getCurrentSession().update(entity);
    }

    public UserRole findById(int id) {
        UserRole UserRole = (UserRole) getCurrentSession().get(UserRole.class, id);
        return UserRole;
    }

    public void delete(UserRole entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<UserRole> findAll() {
        List<UserRole> UserRoles = (List<UserRole>) getCurrentSession().createQuery("from UserRole").list();
        return UserRoles;
    }

    public void deleteAll() {
        List<UserRole> entityList = findAll();
        for (UserRole entity : entityList) {
            delete(entity);
        }
    }
}
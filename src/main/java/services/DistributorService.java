package services;

import dao.DistributorDao;
import entities.Distributor;
import entities.User;

import java.util.List;

public class DistributorService {
    private static DistributorDao distributorDao;

    public DistributorService() {
        distributorDao = new DistributorDao();
    }

    public void persist(Distributor entity) {
        distributorDao.openCurrentSessionwithTransaction();
        distributorDao.persist(entity);
        distributorDao.closeCurrentSessionwithTransaction();
    }

    public void update(Distributor entity) {
        distributorDao.openCurrentSessionwithTransaction();
        distributorDao.update(entity);
        distributorDao.closeCurrentSessionwithTransaction();
    }

    public Distributor findById(int id) {
        distributorDao.openCurrentSession();
        Distributor distributor = distributorDao.findById(id);
        distributorDao.closeCurrentSession();
        return distributor;
    }
    public Distributor findByUser(User user) {
        distributorDao.openCurrentSession();
        Distributor distributor = distributorDao.findByUser(user);
        distributorDao.closeCurrentSession();
        return distributor;
    }

    public void delete(int id) {
        distributorDao.openCurrentSessionwithTransaction();
        Distributor distributor = distributorDao.findById(id);
        distributorDao.delete(distributor);
        distributorDao.closeCurrentSessionwithTransaction();
    }

    public List<Distributor> findAll() {
        distributorDao.openCurrentSession();
        List<Distributor> distributors = distributorDao.findAll();
        distributorDao.closeCurrentSession();
        return distributors;
    }

    public void deleteAll() {
        distributorDao.openCurrentSessionwithTransaction();
        distributorDao.deleteAll();
        distributorDao.closeCurrentSessionwithTransaction();
    }

    public DistributorDao distributorDao() {
        return distributorDao;
    }
}

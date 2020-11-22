package services;

import dao.DistributorDao;
import entities.Distributor;

import java.util.List;

public class DistributorService {
    private static DistributorDao distributorDao;

    public DistributorService() {
        distributorDao = new DistributorDao();
    }

    public Distributor loadDistributor(int distributorId) {
        distributorDao.openCurrentSession();
        Distributor distributor = distributorDao.openCurrentSession().load(Distributor.class,distributorId);
        distributorDao.closeCurrentSession();
        return distributor;
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

    public Distributor getByUsername(String username)
    {
        distributorDao.openCurrentSession();
        Distributor distributor = distributorDao.getByUsername(username);
        distributorDao.closeCurrentSession();
        return distributor;
    }

    public void setFee(int distributorId,int eventId, float fee)
    {

    }

    public float getFee(int distributorId,int eventId)
    {
        distributorDao.openCurrentSession();
        float fee = distributorDao.getFee(distributorId,eventId);
        distributorDao.closeCurrentSession();
        return fee;
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

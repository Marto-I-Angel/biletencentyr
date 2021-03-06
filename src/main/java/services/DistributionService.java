package services;

import dao.DistributionDao;
import entities.Distribution;
import entities.DistributionID;

import java.util.List;

public class DistributionService {
    private static DistributionDao distributionDao;

    public DistributionService() {
        distributionDao = new DistributionDao();
    }

    public void persist(Distribution entity) {
        distributionDao.openCurrentSessionwithTransaction();
        distributionDao.persist(entity);
        distributionDao.closeCurrentSessionwithTransaction();
    }

    public void update(Distribution entity) {
        distributionDao.openCurrentSessionwithTransaction();
        distributionDao.update(entity);
        distributionDao.closeCurrentSessionwithTransaction();
    }

    public void saveOrUpdate(Distribution entity) {
        distributionDao.openCurrentSessionwithTransaction();
        distributionDao.saveOrUpdate(entity);
        distributionDao.closeCurrentSessionwithTransaction();
    }

    public Distribution findDistribution(int distributorId, int eventId) {
        distributionDao.openCurrentSession();
        Distribution distribution = distributionDao.findDistribution(distributorId, eventId);
        distributionDao.closeCurrentSession();
        return distribution;
    }

    public Distribution findById(int id) {
        distributionDao.openCurrentSession();
        Distribution distribution = distributionDao.findById(id);
        distributionDao.closeCurrentSession();
        return distribution;
    }

    public void delete(DistributionID id) {
        distributionDao.openCurrentSessionwithTransaction();
        Distribution distribution = distributionDao.findById(id);
        distributionDao.delete(distribution);
        distributionDao.closeCurrentSessionwithTransaction();
    }

    public List<Distribution> findAll() {
        distributionDao.openCurrentSession();
        List<Distribution> distributions = distributionDao.findAll();
        distributionDao.closeCurrentSession();
        return distributions;
    }

    public void deleteAll(int eventId) {
        distributionDao.openCurrentSessionwithTransaction();
        distributionDao.deleteAll(eventId);
        distributionDao.closeCurrentSessionwithTransaction();
    }

//    public Distribution loadDistribution(int eventId) {
//        eventDao.openCurrentSession();
//        Event event = eventDao.openCurrentSession().get(Event.class, eventId);
//        eventDao.closeCurrentSession();
//        return event;
//    }

    public DistributionDao distributionDao() {
        return distributionDao;
    }

}

package services;

import dao.DistributionDao;
import entities.Distribution;
import entities.Event;

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

        public Distribution findById(int id) {
            distributionDao.openCurrentSession();
            Distribution distribution = distributionDao.findById(id);
            distributionDao.closeCurrentSession();
            return distribution;
        }

        public List<Event> findByDistributorId(int id) {
            distributionDao.openCurrentSession();
            List<Event> events = distributionDao.findByDistributorId(id);
            distributionDao.closeCurrentSession();
            return events;
        }

        public void delete(int id) {
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

        public DistributionDao distributionDao() {
            return distributionDao;
        }

}

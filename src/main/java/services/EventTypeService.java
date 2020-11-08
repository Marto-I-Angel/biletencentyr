package services;

import dao.EventTypeDao;
import entities.EventType;

import java.util.List;

public class EventTypeService {
    private static EventTypeDao hostDao;

    public EventTypeService() {
        hostDao = new EventTypeDao();
    }

    public void persist(EventType entity) {
        hostDao.openCurrentSessionwithTransaction();
        hostDao.persist(entity);
        hostDao.closeCurrentSessionwithTransaction();
    }

    public void update(EventType entity) {
        hostDao.openCurrentSessionwithTransaction();
        hostDao.update(entity);
        hostDao.closeCurrentSessionwithTransaction();
    }

    public EventType findById(int id) {
        hostDao.openCurrentSession();
        EventType host = hostDao.findById(id);
        hostDao.closeCurrentSession();
        return host;
    }

    public void delete(int id) {
        hostDao.openCurrentSessionwithTransaction();
        EventType host = hostDao.findById(id);
        hostDao.delete(host);
        hostDao.closeCurrentSessionwithTransaction();
    }

    public List<EventType> findAll() {
        hostDao.openCurrentSession();
        List<EventType> hosts = hostDao.findAll();
        hostDao.closeCurrentSession();
        return hosts;
    }

    public void deleteAll() {
        hostDao.openCurrentSessionwithTransaction();
        hostDao.deleteAll();
        hostDao.closeCurrentSessionwithTransaction();
    }

    public EventTypeDao hostDao() {
        return hostDao;
    }
}

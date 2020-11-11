package services;

import dao.EventDao;
import entities.Event;

import java.util.List;

public class EventService {
    private static EventDao eventDao;

    public EventService() {
        eventDao = new EventDao();
    }

    public void persist(Event entity) {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.persist(entity);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public void update(Event entity) {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.update(entity);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public Event findById(int id) {
        eventDao.openCurrentSession();
        Event event = eventDao.findById(id);
        eventDao.closeCurrentSession();
        return event;
    }

    public void delete(int id) {
        eventDao.openCurrentSessionwithTransaction();
        Event event = eventDao.findById(id);
        eventDao.delete(event);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public List<Event> findAll() {
        eventDao.openCurrentSession();
        List<Event> events = eventDao.findAll();
        eventDao.closeCurrentSession();
        return events;
    }

    public void deleteAll() {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.deleteAll();
        eventDao.closeCurrentSessionwithTransaction();
    }

    public EventDao eventDao() {
        return eventDao;
    }
}
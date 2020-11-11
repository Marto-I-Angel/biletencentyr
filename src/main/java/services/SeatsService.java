package services;

import dao.SeatsDao;
import entities.Seats;

import java.util.List;

public class SeatsService {
    private static SeatsDao userDao;

    public SeatsService() {
        userDao = new SeatsDao();
    }

    public void persist(Seats entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.persist(entity);
        userDao.closeCurrentSessionwithTransaction();
    }

    public void update(Seats entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.update(entity);
        userDao.closeCurrentSessionwithTransaction();
    }

    public Seats findById(int id) {
        userDao.openCurrentSession();
        Seats user = userDao.findById(id);
        userDao.closeCurrentSession();
        return user;
    }

    public void delete(int id) {
        userDao.openCurrentSessionwithTransaction();
        Seats user = userDao.findById(id);
        userDao.delete(user);
        userDao.closeCurrentSessionwithTransaction();
    }

    public List<Seats> findAll() {
        userDao.openCurrentSession();
        List<Seats> users = userDao.findAll();
        userDao.closeCurrentSession();
        return users;
    }

    public void deleteAll() {
        userDao.openCurrentSessionwithTransaction();
        userDao.deleteAll();
        userDao.closeCurrentSessionwithTransaction();
    }

    public SeatsDao userDao() {
        return userDao;
    }
}

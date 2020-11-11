package services;

import dao.HostDao;
import entities.Host;
import entities.User;

import java.util.List;

public class HostService {
    private static HostDao hostDao;

    public HostService() {
        hostDao = new HostDao();
    }

    public void persist(Host entity) {
        hostDao.openCurrentSessionwithTransaction();
        hostDao.persist(entity);
        hostDao.closeCurrentSessionwithTransaction();
    }

    public void update(Host entity) {
        hostDao.openCurrentSessionwithTransaction();
        hostDao.update(entity);
        hostDao.closeCurrentSessionwithTransaction();
    }

    public Host findById(int id) {
        hostDao.openCurrentSession();
        Host host = hostDao.findById(id);
        hostDao.closeCurrentSession();
        return host;
    }

    public void delete(int id) {
        hostDao.openCurrentSessionwithTransaction();
        Host host = hostDao.findById(id);
        hostDao.delete(host);
        hostDao.closeCurrentSessionwithTransaction();
    }

    public List<Host> findAll() {
        hostDao.openCurrentSession();
        List<Host> hosts = hostDao.findAll();
        hostDao.closeCurrentSession();
        return hosts;
    }

    public Host getByUsername(String username)
    {
        hostDao.openCurrentSession();
        return hostDao.getByUsername(username);
    }

    public void deleteAll() {
        hostDao.openCurrentSessionwithTransaction();
        hostDao.deleteAll();
        hostDao.closeCurrentSessionwithTransaction();
    }

    public HostDao hostDao() {
        return hostDao;
    }

}

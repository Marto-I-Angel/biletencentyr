package services;

import dao.UserRoleDao;
import entities.UserRole;

import java.util.List;

public class UserRoleService {
    private static UserRoleDao userRoleDao;

    public UserRoleService() {
        userRoleDao = new UserRoleDao();
    }

    public void persist(UserRole entity) {
        userRoleDao.openCurrentSessionwithTransaction();
        userRoleDao.persist(entity);
        userRoleDao.closeCurrentSessionwithTransaction();
    }

    public void update(UserRole entity) {
        userRoleDao.openCurrentSessionwithTransaction();
        userRoleDao.update(entity);
        userRoleDao.closeCurrentSessionwithTransaction();
    }

    public UserRole findById(int id) {
        userRoleDao.openCurrentSession();
        UserRole userRole = userRoleDao.findById(id);
        userRoleDao.closeCurrentSession();
        return userRole;
    }

    public void delete(int id) {
        userRoleDao.openCurrentSessionwithTransaction();
        UserRole userRole = userRoleDao.findById(id);
        userRoleDao.delete(userRole);
        userRoleDao.closeCurrentSessionwithTransaction();
    }

    public List<UserRole> findAll() {
        userRoleDao.openCurrentSession();
        List<UserRole> userRoles = userRoleDao.findAll();
        userRoleDao.closeCurrentSession();
        return userRoles;
    }

    public void deleteAll() {
        userRoleDao.openCurrentSessionwithTransaction();
        userRoleDao.deleteAll();
        userRoleDao.closeCurrentSessionwithTransaction();
    }

    public UserRoleDao userRoleDao() {
        return userRoleDao;
    }
}

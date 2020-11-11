package dao.interfaces;

import entities.User;

import java.util.List;

public interface CustomerDaoInterface<T> {

    T findByUser(User entity);

}

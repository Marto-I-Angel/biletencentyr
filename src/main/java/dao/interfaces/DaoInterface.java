package dao.interfaces;

import java.util.List;

public interface DaoInterface<T> {
    void persist(T entity);

    void update(T entity);

    T findById(int id);

    void delete(T entity);

    List<T> findAll();

    void deleteAll();
}
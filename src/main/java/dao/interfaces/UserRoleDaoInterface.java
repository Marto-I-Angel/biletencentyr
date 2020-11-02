package dao.Interfaces;

import java.util.List;

public interface UserRoleDaoInterface<T> {
    public void persist(T entity);

    public void update(T entity);

    public T findById(int id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();
}
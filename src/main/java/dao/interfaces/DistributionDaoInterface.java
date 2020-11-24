package dao.interfaces;

import java.util.List;

public interface DistributionDaoInterface<T> {
    List<T> findByDistributorId(int id);
}

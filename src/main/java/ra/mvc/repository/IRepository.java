package ra.mvc.repository;

import java.util.List;

public interface IRepository<T,E> {
    List<T> findAll();
    T findById(E id);
    void save(T t);
    void deleteById(E id);
}

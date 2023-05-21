package idigitallegacy.root.application.service.property;

import java.util.List;
import java.util.Optional;

public interface IPropertyService<T> {
    List<T> getAll();
    Optional<T> getById(Integer id);
    Optional<T> getByName(String name);
    void add(T entity);
    void removeById(Integer id);
    void removeByName(String name);
}

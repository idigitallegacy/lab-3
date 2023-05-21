package idigitallegacy.root.application.service.entity;

import java.util.List;
import java.util.Optional;

public interface IEntityService<T> {
    List<T> getAll();
    Optional<T> getById(Integer id);
    Optional<T> getByName(String name);
    List<T> getAllByRelatedEntityId(Integer relatedEntityId);
    void add(T entity);
    void removeById(Integer id);
    void removeByName(String name);
    void removeByRelatedEntityId(Integer relatedEntityId);
}

package idigitallegacy.root.application.service.entity;

import idigitallegacy.root.application.entity.CatsEntity;
import idigitallegacy.root.application.service.property.IPropertyService;
import idigitallegacy.root.application.wrapper.CatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatsService implements IEntityService<CatsEntity> {
    @Autowired
    private CatsRepository catsRepository;

    @Override
    public List<CatsEntity> getAll() {
        return catsRepository.findAll();
    }

    @Override
    public Optional<CatsEntity> getById(Integer id) {
        return catsRepository.findById(id);
    }

    @Override
    public Optional<CatsEntity> getByName(String name) {
        return catsRepository.findByCatName(name);
    }

    @Override
    public List<CatsEntity> getAllByRelatedEntityId(Integer relatedEntityId) {
        return catsRepository.findByCatOwnerId(relatedEntityId).stream().toList();
    }

    @Override
    public void add(CatsEntity entity) {
        catsRepository.save(entity);
    }

    @Override
    public void removeById(Integer id) {
        catsRepository.deleteById(id);
    }

    @Override
    public void removeByName(String name) {
        catsRepository.removeCatsEntityByCatName(name);
    }

    @Override
    public void removeByRelatedEntityId(Integer relatedEntityId) {
        catsRepository.removeByCatOwnerId(relatedEntityId);
    }
}


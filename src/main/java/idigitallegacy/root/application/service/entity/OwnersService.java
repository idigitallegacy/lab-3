package idigitallegacy.root.application.service.entity;


import idigitallegacy.root.application.entity.OwnersEntity;
import idigitallegacy.root.application.wrapper.OwnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnersService implements IEntityService<OwnersEntity> {
    @Autowired
    private OwnersRepository ownersRepository;

    @Override
    public List<OwnersEntity> getAll() {
        return ownersRepository.findAll();
    }

    @Override
    public Optional<OwnersEntity> getById(Integer id) {
        return ownersRepository.findById(id);
    }

    @Override
    public Optional<OwnersEntity> getByName(String name) {
        return ownersRepository.findByOwnerName(name);
    }

    @Override
    public List<OwnersEntity> getAllByRelatedEntityId(Integer relatedEntityId) {
        return ownersRepository.findByCatId(relatedEntityId).stream().toList();
    }

    @Override
    public void add(OwnersEntity entity) {
        ownersRepository.save(entity);
    }

    @Override
    public void removeById(Integer id) {
        ownersRepository.deleteById(id);
    }

    @Override
    public void removeByName(String name) {
        ownersRepository.removeByName(name);
    }

    @Override
    public void removeByRelatedEntityId(Integer relatedEntityId) {
        ownersRepository.removeByCatId(relatedEntityId);
    }
}
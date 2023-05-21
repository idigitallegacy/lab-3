package idigitallegacy.root.application.service.relation;

import idigitallegacy.root.application.entity.CatsAndOwnersEntity;
import idigitallegacy.root.application.entity.FriendCatsEntity;
import idigitallegacy.root.application.wrapper.CatsAndOwnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatsAndOwnersService implements ICatsAndOwnersService {
    @Autowired
    private CatsAndOwnersRepository relationRepository;

    @Override
    public List<CatsAndOwnersEntity> getAll() {
        return relationRepository.findAll();
    }

    @Override
    public List<CatsAndOwnersEntity> getByCatId(Integer catId) {
        return relationRepository.findByCatId(catId).stream().toList();
    }

    @Override
    public List<CatsAndOwnersEntity> getByCatName(String catName) {
        return relationRepository.findByCatName(catName).stream().toList();
    }

    @Override
    public List<CatsAndOwnersEntity> getByOwnerId(Integer ownerId) {
        return relationRepository.findByOwnerId(ownerId).stream().toList();
    }

    @Override
    public List<CatsAndOwnersEntity> getByOwnerName(String ownerName) {
        return relationRepository.findByOwnerName(ownerName).stream().toList();
    }

    @Override
    public void add(CatsAndOwnersEntity entity) {
        relationRepository.save(entity);
    }

    @Override
    public void removeByCatId(Integer catId) {
        relationRepository.removeByCatId(catId);
    }

    @Override
    public void removeByCatName(String catName) {
        relationRepository.removeByCatName(catName);
    }

    @Override
    public void removeByOwnerId(Integer ownerId) {
        relationRepository.removeByOwnerId(ownerId);
    }

    @Override
    public void removeByOwnerName(String ownerName) {
        relationRepository.removeByOwnerName(ownerName);
    }

    @Override
    public void remove(CatsAndOwnersEntity relation) {
        relationRepository.delete(relation);
    }
}

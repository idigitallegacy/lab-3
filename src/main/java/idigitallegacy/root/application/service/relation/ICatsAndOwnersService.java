package idigitallegacy.root.application.service.relation;

import idigitallegacy.root.application.entity.CatsAndOwnersEntity;
import idigitallegacy.root.application.entity.FriendCatsEntity;

import java.util.List;

public interface ICatsAndOwnersService {
    List<CatsAndOwnersEntity> getAll();
    List<CatsAndOwnersEntity> getByCatId(Integer catId);
    List<CatsAndOwnersEntity> getByCatName(String catName);

    List<CatsAndOwnersEntity> getByOwnerId(Integer ownerId);

    List<CatsAndOwnersEntity> getByOwnerName(String ownerName);

    void add(CatsAndOwnersEntity entity);
    void removeByCatId(Integer catId);
    void removeByCatName(String catName);

    void removeByOwnerId(Integer ownerId);

    void removeByOwnerName(String ownerName);
    void remove(CatsAndOwnersEntity relation);
}

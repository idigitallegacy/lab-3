package idigitallegacy.root.application.service.relation;

import idigitallegacy.root.application.entity.FriendCatsEntity;
import idigitallegacy.root.application.entity.key.FriendCatsEntityPK;

import java.util.List;
import java.util.Optional;

public interface IFriendsService {
    List<FriendCatsEntity> getAll();
    List<FriendCatsEntity> getById(Integer id);
    void add(FriendCatsEntity friendship);
    void removeById(Integer id);
    void remove(FriendCatsEntity friendship);
}

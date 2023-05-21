package idigitallegacy.root.application.service.relation;

import idigitallegacy.root.application.entity.FriendCatsEntity;
import idigitallegacy.root.application.entity.key.FriendCatsEntityPK;
import idigitallegacy.root.application.wrapper.FriendCatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendCatsService implements IFriendsService {
    @Autowired
    private FriendCatsRepository friendCatsRepository;

    @Override
    public List<FriendCatsEntity> getAll() {
        return friendCatsRepository.findAll();
    }

    @Override
    public List<FriendCatsEntity> getById(Integer id) {
        return friendCatsRepository.findById(id).stream().toList();
    }

    @Override
    public void add(FriendCatsEntity friendship) {
        friendCatsRepository.save(friendship);
    }

    @Override
    public void removeById(Integer id) {
        friendCatsRepository.removeById(id);
    }

    @Override
    public void remove(FriendCatsEntity friendship) {
        friendCatsRepository.delete(friendship);
    }
}

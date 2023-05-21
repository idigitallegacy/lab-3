package idigitallegacy.root.application.wrapper;

import idigitallegacy.root.application.entity.FriendCatsEntity;
import idigitallegacy.root.application.entity.OwnersEntity;
import idigitallegacy.root.application.entity.key.FriendCatsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface FriendCatsRepository extends JpaRepository<FriendCatsEntity, FriendCatsEntityPK> {
    @Query("SELECT t FROM FriendCatsEntity t WHERE t.catId = ?1 OR t.friendId = ?1")
    Collection<FriendCatsEntity> findById(Integer id);

    @Query("DELETE FROM FriendCatsEntity t WHERE t.catId = ?1 OR t.friendId = ?1")
    void removeById(Integer ownerId);
}

package idigitallegacy.root.application.wrapper;

import idigitallegacy.root.application.entity.OwnersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface OwnersRepository extends JpaRepository<OwnersEntity, Integer> {
    @Query("SELECT t FROM OwnersEntity t WHERE t.ownerName = ?1")
    Optional<OwnersEntity> findByOwnerName(String name);

    @Query("SELECT t FROM OwnersEntity t WHERE t.ownerId = (SELECT c.catOwnerId FROM CatsEntity c WHERE c.catId = ?1)")
    Collection<OwnersEntity> findByCatId(Integer catId);

    @Query("DELETE FROM OwnersEntity t WHERE t.ownerName = ?1")
    void removeByName(String name);

    @Query("DELETE FROM OwnersEntity t WHERE t.ownerId = ?1")
    void removeByCatId(Integer ownerId);
}

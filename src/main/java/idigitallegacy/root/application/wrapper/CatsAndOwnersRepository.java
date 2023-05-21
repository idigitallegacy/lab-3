package idigitallegacy.root.application.wrapper;

import idigitallegacy.root.application.entity.CatsAndOwnersEntity;
import idigitallegacy.root.application.entity.CatsEntity;
import idigitallegacy.root.application.entity.key.CatsAndOwnersEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CatsAndOwnersRepository extends JpaRepository<CatsAndOwnersEntity, CatsAndOwnersEntityPK> {
    @Query("SELECT t FROM CatsAndOwnersEntity t WHERE t.catId = ?1")
    Collection<CatsAndOwnersEntity> findByCatId(Integer id);

    @Query("SELECT t FROM CatsAndOwnersEntity t WHERE t.catId = (SELECT c.catId FROM CatsEntity c WHERE c.catName = ?1)")
    Collection<CatsAndOwnersEntity> findByCatName(String catName);

    @Query("SELECT t FROM CatsAndOwnersEntity t WHERE t.ownerId = ?1")
    Collection<CatsAndOwnersEntity> findByOwnerId(Integer id);

    @Query("SELECT t FROM CatsAndOwnersEntity t WHERE t.catId = (SELECT o.ownerId FROM OwnersEntity o WHERE o.ownerName = ?1)")
    Collection<CatsAndOwnersEntity> findByOwnerName(String ownerName);

    @Query("DELETE FROM CatsAndOwnersEntity t WHERE t.catId = ?1")
    void removeByCatId(Integer id);

    @Query("DELETE FROM CatsAndOwnersEntity t WHERE t.catId = (SELECT c.catId FROM CatsEntity c WHERE c.catName = ?1)")
    void removeByCatName(String name);

    @Query("DELETE FROM CatsAndOwnersEntity t WHERE t.ownerId = ?1")
    void removeByOwnerId(Integer id);

    @Query("DELETE FROM CatsAndOwnersEntity t WHERE t.ownerId = (SELECT o.ownerId FROM OwnersEntity o WHERE o.ownerName = ?1)")
    void removeByOwnerName(String name);
}

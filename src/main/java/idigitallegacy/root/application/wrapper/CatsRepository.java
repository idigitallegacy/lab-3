package idigitallegacy.root.application.wrapper;

import idigitallegacy.root.application.entity.CatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CatsRepository extends JpaRepository<CatsEntity, Integer> {
    @Query("SELECT t FROM CatsEntity t WHERE t.catName = ?1")
    Optional<CatsEntity> findByCatName(String name);

    @Query("SELECT t FROM CatsEntity t WHERE t.catOwnerId = ?1")
    Collection<CatsEntity> findByCatOwnerId(Integer ownerId);

    @Query("DELETE FROM CatsEntity t WHERE t.catName = ?1")
    void removeCatsEntityByCatName(String name);

    @Query("DELETE FROM CatsEntity t WHERE t.catOwnerId = ?1")
    void removeByCatOwnerId(Integer ownerId);
}

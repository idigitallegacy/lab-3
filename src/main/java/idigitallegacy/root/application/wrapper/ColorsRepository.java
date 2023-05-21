package idigitallegacy.root.application.wrapper;

import idigitallegacy.root.application.entity.ColorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorsRepository extends JpaRepository<ColorsEntity, Integer> {
    @Query("SELECT t FROM ColorsEntity t WHERE t.colorName = ?1")
    Optional<ColorsEntity> findByBreedName(String name);

    @Query("DELETE FROM ColorsEntity t WHERE t.colorName = ?1")
    void removeBreedsEntitiesByBreedName(String name);
}
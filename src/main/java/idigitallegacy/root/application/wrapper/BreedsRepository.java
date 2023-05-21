package idigitallegacy.root.application.wrapper;

import idigitallegacy.root.application.entity.BreedsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreedsRepository extends JpaRepository<BreedsEntity, Integer> {
    @Query("SELECT t FROM BreedsEntity t WHERE t.breedName = ?1")
    Optional<BreedsEntity> findByBreedName(String name);

    @Query("DELETE FROM BreedsEntity t WHERE t.breedName = ?1")
    void removeBreedsEntitiesByBreedName(String name);
}

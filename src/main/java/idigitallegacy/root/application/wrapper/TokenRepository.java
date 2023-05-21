package idigitallegacy.root.application.wrapper;

import idigitallegacy.root.application.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    Optional<TokenEntity> findByToken(String token);
    Optional<TokenEntity> findByUserId(Long userId);
    int deleteByUserId(Long userId);
}
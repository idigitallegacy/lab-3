package idigitallegacy.root.application.service.auth;

import idigitallegacy.root.application.entity.TokenEntity;
import org.springframework.stereotype.Service;

@Service
public interface IRefreshTokenService {
    TokenEntity findByToken(String token);

    TokenEntity findByUserId(Long userId);

    TokenEntity createRefreshToken(Long userId);

    TokenEntity verifyExpiration(TokenEntity token);

    int deleteByUserId(Long userId);
}
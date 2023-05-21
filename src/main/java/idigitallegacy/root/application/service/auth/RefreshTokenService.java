package idigitallegacy.root.application.service.auth;

import idigitallegacy.root.application.entity.TokenEntity;
import idigitallegacy.root.application.entity.UserEntity;
import idigitallegacy.root.application.exceptions.ForbiddenException;
import idigitallegacy.root.application.wrapper.UserRepository;
import idigitallegacy.root.application.wrapper.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements IRefreshTokenService {

    private Long refreshTokenDurationMs;

    private final TokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    @Override
    public TokenEntity findByToken(String token) {
        Optional<TokenEntity> refreshTokenEntityOptional = refreshTokenRepository.findByToken(token);
        return refreshTokenEntityOptional.orElse(null);
    }

    @Override
    public TokenEntity findByUserId(Long userId) {
        Optional<TokenEntity> refreshTokenEntityOptional = refreshTokenRepository.findByUserId(userId);
        if (refreshTokenEntityOptional.isPresent()) {
            TokenEntity refreshTokenEntity = refreshTokenEntityOptional.get();
            try{
                return this.verifyExpiration(refreshTokenEntity);
            }catch (ForbiddenException e){
                return null;
            }
        }
        return null;
    }

    @Override
    public TokenEntity createRefreshToken(Long userId) {
        TokenEntity refreshToken = new TokenEntity();
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            refreshToken.setUserId(userEntityOptional.get().getId());
            refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken = refreshTokenRepository.save(refreshToken);
            return refreshToken;
        }
        return null;
    }

    @Override
    public TokenEntity verifyExpiration(TokenEntity token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new ForbiddenException("Refresh token was expired. Please make a new log in request");
        }
        return token;
    }

    @Override
    public int deleteByUserId(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            return refreshTokenRepository.deleteByUserId(userEntityOptional.get().getId());
        }
        throw new NotFoundException("");
    }
}
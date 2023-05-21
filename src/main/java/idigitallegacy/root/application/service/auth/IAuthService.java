package idigitallegacy.root.application.service.auth;

import idigitallegacy.root.application.dto.AuthDto;
import idigitallegacy.root.application.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {
    UserEntity registration(AuthDto authDto);

    UserEntity login(AuthDto authDto);
}
package idigitallegacy.root.application.service.auth;

import idigitallegacy.root.application.dto.AuthDto;
import idigitallegacy.root.application.entity.UserEntity;
import idigitallegacy.root.application.exceptions.BadRequestException;
import idigitallegacy.root.application.wrapper.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserEntity registration(AuthDto authDto) {
        UserEntity userEntity = userRepository.findByEmail(authDto.getEmail());
        if (userEntity != null) {
            throw new BadRequestException("User is already registered");
        }
        String passwordHash = passwordEncoder.encode(authDto.getPassword());
        return userRepository.save(new UserEntity(authDto.getUsername(), authDto.getEmail(), passwordHash));
    }

    @Override
    public UserEntity login(AuthDto authDto) {
        UserEntity userEntity = userRepository.findByEmail(authDto.getEmail());
        if (userEntity == null) {
            throw new NotFoundException("User with email: " + authDto.getEmail() + " is not registered");
        }
        if (!passwordEquals(userEntity.getPassword(), authDto.getPassword())) {
            throw new BadRequestException("Wrong password or email");
        }
        return userEntity;
    }

    private boolean passwordEquals(String password, String passwordNew) {
        return passwordEncoder.matches(passwordNew, password);
    }
}
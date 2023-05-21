package idigitallegacy.root.application.service.auth;

import idigitallegacy.root.application.entity.RoleEntity;
import idigitallegacy.root.application.entity.UserEntity;
import idigitallegacy.root.application.dto.UserDto;
import idigitallegacy.root.application.mapper.UserMapper;
import idigitallegacy.root.application.exceptions.BadRequestException;
import idigitallegacy.root.application.wrapper.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUser(UserDto userDto) {
        String email = userDto.getEmail();
        if (userRepository.findByEmail(email) != null) {
            throw new BadRequestException("User already exists");
        }
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userEntity.setActive(true);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.getRoles().add(RoleEntity.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        return userRepository.save(userEntity);
    }

    @Override
    public void changeUserRoles(UserDto userDto, Map<String, String> form) {
        Set<String> roles = Arrays.stream(RoleEntity.values())
                .map(RoleEntity::name)
                .collect(Collectors.toSet());
        userDto.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                userDto.getRoles().add(RoleEntity.valueOf(key));
            }
        }
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userRepository.save(userEntity);
    }

    @Override
    public void banUser(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        assert user != null;
        userRepository.save(user);
    }

    @Override
    public UserEntity getUserByPrincipal(Principal principal) {
        if (principal == null) return new UserEntity();
        return userRepository.findByEmail(principal.getName());
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
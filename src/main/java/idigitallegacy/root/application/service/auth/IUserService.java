package idigitallegacy.root.application.service.auth;

import idigitallegacy.root.application.entity.UserEntity;
import idigitallegacy.root.application.dto.UserDto;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Service
public interface IUserService {
    UserEntity createUser(UserDto userDto);

    UserEntity findByEmail(String email);

    UserEntity findById(Long id);

    List<UserDto> findAllUsers();

    UserEntity getUserByPrincipal(Principal principal);

    void banUser(Long id);

    void changeUserRoles(UserDto userDto, Map<String, String> form);
}
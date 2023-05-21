package idigitallegacy.root.application.controller;

import idigitallegacy.root.application.entity.RoleEntity;
import idigitallegacy.root.application.entity.UserEntity;
import idigitallegacy.root.application.dto.UserDto;
import idigitallegacy.root.application.mapper.UserMapper;
import idigitallegacy.root.application.service.auth.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Administration")
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserDto> users = userService.findAllUsers();
        List<UserEntity> userDtos = users.stream().map(userMapper.INSTANCE::toUserEntity).collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping("/users/{id}/roles/add")
    public ResponseEntity<?> assignRoles(@PathVariable("id") Long id, @RequestBody List<String> roles) {
        UserEntity user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        Set<RoleEntity> userRoles = new HashSet<>();
        for (String role : roles) {
            userRoles.add(RoleEntity.valueOf(role));
        }
        user.setRoles(userRoles);
        userService.createUser(userMapper.toDTO(user));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/ban/{id}")
    public ResponseEntity<?> banUser(@PathVariable("id") Long id) {
        UserEntity user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        user.setActive(false);
        userService.createUser(userMapper.toDTO(user));
        return ResponseEntity.ok().build();
    }
}
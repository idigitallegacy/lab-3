package idigitallegacy.root.application.dto;

import idigitallegacy.root.application.entity.OwnersEntity;
import idigitallegacy.root.application.entity.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {
    @NotNull
    @NotEmpty
    private Long id;

    @NotEmpty(message = "Username must not be empty")
    private String username;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty
    private boolean active;

    @NotEmpty
    @NotBlank
    private Set<RoleEntity> roles;

    @NotBlank
    @NotNull
    private OwnersEntity owner;

    public UserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
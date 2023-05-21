package idigitallegacy.root.application.mapper;

import idigitallegacy.root.application.entity.UserEntity;
import idigitallegacy.root.application.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IUserMapper {
    @Override
    public UserDto toDTO(UserEntity userEntity) {
        UserDto dto = new UserDto();
        dto.setUsername(userEntity.getUsername());
        dto.setPassword(userEntity.getPassword());
        dto.setEmail(userEntity.getEmail());
        dto.setActive(true);
        dto.setRoles(userEntity.getRoles());
        return dto;
    }

    @Override
    public UserEntity toUserEntity(UserDto authDto) {
        UserEntity user = new UserEntity();
        user.setUsername(authDto.getUsername());
        user.setPassword(authDto.getPassword());
        user.setEmail(authDto.getEmail());
        user.setActive(true);
        user.setRoles(authDto.getRoles());
        return user;
    }
}
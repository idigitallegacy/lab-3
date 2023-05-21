package idigitallegacy.root.application.mapper;

import idigitallegacy.root.application.entity.UserEntity;
import idigitallegacy.root.application.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserDto toDTO(UserEntity userEntity);

    UserEntity toUserEntity(UserDto authDto);
}
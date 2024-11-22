package com.course.fleet.management.api.mapper;

import com.course.fleet.management.api.domain.User;
import com.course.fleet.management.api.dto.request.UserCreateRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateAttributeRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateRequestDTO;
import com.course.fleet.management.api.dto.response.UserCreateResponseDTO;
import com.course.fleet.management.api.dto.response.UserResponseDTO;
import com.course.fleet.management.api.dto.response.UserUpdateAttributeResponseDTO;
import com.course.fleet.management.api.dto.response.UserUpdateResponseDTO;
import com.course.fleet.management.api.entity.UserEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserResponseDTO toUserResponseDTO(User user);

  List<UserResponseDTO> toListUserResponseDTO(List<User> userList);

  @Mapping(target = "id", ignore = true)
  User toUser(UserCreateRequestDTO userCreateRequestDTO);

  UserCreateResponseDTO toUserCreateResponseDTO(User user);

  // Mapping for request DTO to User, to update fields in user

  User toUserUpdate(UserUpdateRequestDTO userUpdateRequestDTO, @MappingTarget User user);

  UserUpdateResponseDTO toUserUpdateResponseDTO(User user);

  // Mapping for request DTO to User, to update fields in user

  @Mapping(
      target = "email",
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(
      target = "name",
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  User toUserUpdateAttribute(
      UserUpdateAttributeRequestDTO userUpdateAttributeRequestDTO, @MappingTarget User user);

  UserUpdateAttributeResponseDTO toUserUpdateAttributeResponseDTO(User user);

  UserEntity toUserEntity(User user);

  User toUser(UserEntity userEntity);

  default List<User> toUserList(List<UserEntity> userEntities) {
    return userEntities.stream().map(this::toUser).collect(Collectors.toList());
  }
}

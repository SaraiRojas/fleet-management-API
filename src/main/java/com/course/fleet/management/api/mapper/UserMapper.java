package com.course.fleet.management.api.mapper;

import com.course.fleet.management.api.domain.User;
import com.course.fleet.management.api.dto.request.UserCreateRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateAttributeRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateRequestDTO;
import com.course.fleet.management.api.dto.response.UserCreateResponseDTO;
import com.course.fleet.management.api.dto.response.UserResponseDTO;
import com.course.fleet.management.api.dto.response.UserUpdateAttributeResponseDTO;
import com.course.fleet.management.api.dto.response.UserUpdateResponseDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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

  void toUserUpdate(UserUpdateRequestDTO userUpdateRequestDTO, @MappingTarget User user);

  UserUpdateResponseDTO toUserUpdateResponseDTO(User user);

  // Mapping for request DTO to User, to update fields in user

  void toUserUpdateAttribute(
      UserUpdateAttributeRequestDTO userUpdateAttributeRequestDTO, @MappingTarget User user);

  UserUpdateAttributeResponseDTO toUserUpdateAttributeResponseDTO(User user);
}

package com.course.fleet.management.api.controller;

import static com.course.fleet.management.api.constants.UriConstant.USER_BASE_URL;
import static com.course.fleet.management.api.constants.UriConstant.USER_BY_ID;
import static com.course.fleet.management.api.constants.UriConstant.USER_DELETE;
import static com.course.fleet.management.api.constants.UriConstant.USER_GET_ALL;
import static com.course.fleet.management.api.constants.UriConstant.USER_UPDATE;
import static com.course.fleet.management.api.constants.UriConstant.USER_UPDATE_ATTRIBUTE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.course.fleet.management.api.domain.User;
import com.course.fleet.management.api.dto.request.UserCreateRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateAttributeRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateRequestDTO;
import com.course.fleet.management.api.dto.response.UserCreateResponseDTO;
import com.course.fleet.management.api.dto.response.UserResponseDTO;
import com.course.fleet.management.api.dto.response.UserUpdateAttributeResponseDTO;
import com.course.fleet.management.api.dto.response.UserUpdateResponseDTO;
import com.course.fleet.management.api.mapper.UserMapper;
import com.course.fleet.management.api.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = USER_BASE_URL)
public class UserController {

  private final UserService userService;

  @GetMapping(value = USER_GET_ALL, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserResponseDTO>> getUsers() {
    final List<User> userList = userService.getAll();
    final List<UserResponseDTO> userListResponseDTO =
        UserMapper.INSTANCE.toListUserResponseDTO(userList);
    return ResponseEntity.ok(userListResponseDTO);
  }

  @GetMapping(value = USER_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
    final User userById = userService.getUser(id);
    final UserResponseDTO userResponseDTO = UserMapper.INSTANCE.toUserResponseDTO(userById);
    return ResponseEntity.ok(userResponseDTO);
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserCreateResponseDTO> createUser(
      @RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
    User newUser = UserMapper.INSTANCE.toUser(userCreateRequestDTO);
    newUser = userService.create(newUser);
    final UserCreateResponseDTO userCreateResponseDTO =
        UserMapper.INSTANCE.toUserCreateResponseDTO(newUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreateResponseDTO);
  }

  @PutMapping(
      value = USER_UPDATE,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserUpdateResponseDTO> updateUser(
      @PathVariable Long id, @RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO) {
    final User user = userService.updateUser(userUpdateRequestDTO, id);
    final UserUpdateResponseDTO userUpdateResponseDTO =
        UserMapper.INSTANCE.toUserUpdateResponseDTO(user);
    return ResponseEntity.ok(userUpdateResponseDTO);
  }

  @PatchMapping(
      value = USER_UPDATE_ATTRIBUTE,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserUpdateAttributeResponseDTO> updateUserAttribute(
      @PathVariable Long id,
      @RequestBody @Valid UserUpdateAttributeRequestDTO userUpdateAttributeRequestDTO) {
    final User user = userService.updateUserAttribute(userUpdateAttributeRequestDTO, id);
    final UserUpdateAttributeResponseDTO userUpdateAttributeResponseDTO =
        UserMapper.INSTANCE.toUserUpdateAttributeResponseDTO(user);
    return ResponseEntity.ok(userUpdateAttributeResponseDTO);
  }

  @DeleteMapping(value = USER_DELETE)
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.notFound().build();
  }
}

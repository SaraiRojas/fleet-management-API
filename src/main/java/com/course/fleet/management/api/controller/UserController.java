package com.course.fleet.management.api.controller;

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
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/all")
  public ResponseEntity<List<UserResponseDTO>> getUsers() {
    final List<User> userList = userService.getAll();
    final List<UserResponseDTO> userListResponseDTO =
        UserMapper.INSTANCE.toListUserResponseDTO(userList);
    return ResponseEntity.ok(userListResponseDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUser(@PathVariable long id) {
    final User userById = userService.getUser(id);
    final UserResponseDTO userResponseDTO = UserMapper.INSTANCE.toUserResponseDTO(userById);
    return ResponseEntity.ok(userResponseDTO);
  }

  @PostMapping
  public ResponseEntity<UserCreateResponseDTO> createUser(
      @RequestBody UserCreateRequestDTO userCreateRequestDTO) {
    User newUser = UserMapper.INSTANCE.toUser(userCreateRequestDTO);
    newUser = userService.create(newUser);
    final UserCreateResponseDTO userCreateResponseDTO =
        UserMapper.INSTANCE.toUserCreateResponseDTO(newUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreateResponseDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserUpdateResponseDTO> updateUser(
      @PathVariable long id, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
    User user = userService.getUser(id);
    UserMapper.INSTANCE.toUserUpdate(userUpdateRequestDTO, user);
    user = userService.updateUser(user);
    final UserUpdateResponseDTO userUpdateResponseDTO =
        UserMapper.INSTANCE.toUserUpdateResponseDTO(user);
    return ResponseEntity.ok(userUpdateResponseDTO);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UserUpdateAttributeResponseDTO> updateUserAttributes(
      @PathVariable long id,
      @RequestBody UserUpdateAttributeRequestDTO userUpdateAttributeRequestDTO) {
    User user = userService.getUser(id);
    UserMapper.INSTANCE.toUserUpdateAttribute(userUpdateAttributeRequestDTO, user);
    user = userService.updateUserAttribute(user);
    final UserUpdateAttributeResponseDTO userUpdateAttributeResponseDTO =
        UserMapper.INSTANCE.toUserUpdateAttributeResponseDTO(user);
    return ResponseEntity.ok(userUpdateAttributeResponseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable long id) {
    userService.deleteUser(id);
    return ResponseEntity.notFound().build();
  }
}

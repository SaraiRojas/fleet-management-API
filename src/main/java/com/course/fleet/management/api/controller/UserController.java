package com.course.fleet.management.api.controller;

import com.course.fleet.management.api.domain.User;
import com.course.fleet.management.api.dto.request.UserCreateRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateAttributeRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateRequestDTO;
import com.course.fleet.management.api.dto.response.UserCreateResponseDTO;
import com.course.fleet.management.api.dto.response.UserResponseDTO;
import com.course.fleet.management.api.dto.response.UserUpdateAttributeResponseDTO;
import com.course.fleet.management.api.dto.response.UserUpdateResponseDTO;
import com.course.fleet.management.api.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<UserResponseDTO>> getUsers() {
    final List<User> userList = userService.getAll();
    final List<UserResponseDTO> userListResponseDTO =
        userList.stream()
            .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail()))
            .collect(Collectors.toList());
    return ResponseEntity.ok(userListResponseDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUser(@PathVariable long id) {
    final User userById = userService.getUser(id);
    final UserResponseDTO userResponseDTO =
        new UserResponseDTO(userById.getId(), userById.getName(), userById.getEmail());
    return ResponseEntity.ok(userResponseDTO);
  }

  @PostMapping
  public ResponseEntity<UserCreateResponseDTO> createUser(
      @RequestBody UserCreateRequestDTO userCreateRequestDTO) {
    User newUser = new User(userCreateRequestDTO.name(), userCreateRequestDTO.email());
    newUser = userService.create(newUser);
    final UserCreateResponseDTO userCreateResponseDTO =
        new UserCreateResponseDTO(newUser.getId(), newUser.getName(), newUser.getEmail());
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreateResponseDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserUpdateResponseDTO> updateUser(
      @PathVariable long id, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
    User user = userService.getUser(id);
    user.setName(userUpdateRequestDTO.name());
    user.setEmail(userUpdateRequestDTO.email());
    user = userService.updateUser(user);
    final UserUpdateResponseDTO userUpdateResponseDTO =
        new UserUpdateResponseDTO(user.getId(), user.getName(), user.getEmail());
    return ResponseEntity.ok(userUpdateResponseDTO);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UserUpdateAttributeResponseDTO> updateUserAttributes(
      @PathVariable long id,
      @RequestBody UserUpdateAttributeRequestDTO userUpdateAttributeRequestDTO) {
    User user = userService.getUser(id);
    user.setName(userUpdateAttributeRequestDTO.name());
    user.setEmail(userUpdateAttributeRequestDTO.email());
    user = userService.updateUserAttribute(user);
    final UserUpdateAttributeResponseDTO userUpdateAttributeResponseDTO =
        new UserUpdateAttributeResponseDTO(user.getId(), user.getName(), user.getEmail());
    return ResponseEntity.ok(userUpdateAttributeResponseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable long id) {
    userService.deleteUser(id);
    return ResponseEntity.notFound().build();
  }
}

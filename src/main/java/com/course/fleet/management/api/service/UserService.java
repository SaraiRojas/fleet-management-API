package com.course.fleet.management.api.service;

import com.course.fleet.management.api.domain.User;
import com.course.fleet.management.api.dto.request.UserUpdateAttributeRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateRequestDTO;
import java.util.List;

public interface UserService {
  User create(User user);

  List<User> getAll();

  User getUser(Long id);

  User updateUser(UserUpdateRequestDTO userUpdateRequestDTO, Long id);

  User updateUserAttribute(UserUpdateAttributeRequestDTO userUpdateAttributeRequestDTO, Long id);

  void deleteUser(Long id);
}

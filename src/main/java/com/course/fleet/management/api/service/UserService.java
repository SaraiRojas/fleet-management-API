package com.course.fleet.management.api.service;

import com.course.fleet.management.api.domain.User;
import java.util.List;

public interface UserService {
  User create(User user);

  List<User> getAll();

  User getUser(long id);

  User updateUser(User user);

  User updateUserAttribute(User user);

  void deleteUser(long id);
}

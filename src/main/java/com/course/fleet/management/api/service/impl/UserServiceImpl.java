package com.course.fleet.management.api.service.impl;

import com.course.fleet.management.api.domain.User;
import com.course.fleet.management.api.exception.customExceptions.UserAlreadyExistsException;
import com.course.fleet.management.api.exception.customExceptions.UserNotFoundException;
import com.course.fleet.management.api.service.UserService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private static final List<User> users;

  static {
    Faker faker = new Faker();
    users =
        IntStream.range(0, 10)
            .mapToObj(
                i -> {
                  String firstName = faker.name().firstName();
                  String lastName = faker.name().lastName();
                  return new User(
                      (long) i,
                      firstName + " " + lastName,
                      firstName + "." + lastName + "@mail.com");
                })
            .collect(Collectors.toList());
  }

  @Override
  public User create(User user) {
    // fake data to be able to test exception
    final String duplicateEmail = "duplicateEmail@email.com";

    if (Objects.equals(user.getEmail(), duplicateEmail)) {
      throw new UserAlreadyExistsException("User already exists");
    }

    return user;
  }

  @Override
  public List<User> getAll() {
    return users;
  }

  @Override
  public User getUser(long id) {
    final User userById;
    userById =
        users.stream()
            .filter(user -> user.getId() == id)
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException("User is not found"));

    return userById;
  }

  @Override
  public User updateUser(User user) {
    return user;
  }

  @Override
  public User updateUserAttribute(User user) {
    return user;
  }

  @Override
  public void deleteUser(long id) {}
}

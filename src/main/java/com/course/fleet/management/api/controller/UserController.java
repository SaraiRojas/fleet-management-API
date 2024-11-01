package com.course.fleet.management.api.controller;

import com.course.fleet.management.api.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.datafaker.Faker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

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

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<User>> getUser(@PathVariable long id) {
    Optional<User> userById = users.stream().filter(user -> user.getId() == id).findFirst();
    return ResponseEntity.ok(userById);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User newUser) {
    // Logic to handle create request
    return ResponseEntity.ok(newUser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
    // Logic to handle update request
    return ResponseEntity.ok(user);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> updateUserAttributes(@PathVariable long id, @RequestBody User user) {
    // Logic to handle patch request
    return ResponseEntity.ok(user);
  }
}

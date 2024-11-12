package com.course.fleet.management.api.controller;

import com.course.fleet.management.api.entity.User;
import net.datafaker.Faker;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/user")
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

  @GetMapping("/all")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable long id) {
    final User userById =
        users.stream()
            .filter(user -> user.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    ;
    return ResponseEntity.ok(userById);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User newUser) {
    // Logic to handle create request
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
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

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable long id) {
    // Logic to handle delete request
    return ResponseEntity.notFound().build();
  }
}

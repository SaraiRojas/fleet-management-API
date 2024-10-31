package com.course.fleet.management.API.controller;

import com.course.fleet.management.API.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.datafaker.Faker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fleet-management-API/users")
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
}

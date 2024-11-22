package com.course.fleet.management.api.dto.request;

import static com.course.fleet.management.api.constants.CustomErrorMessage.VALID_EMAIL;

import jakarta.validation.constraints.Email;

public record UserUpdateAttributeRequestDTO(
    String name, @Email(regexp = ".+@.+\\..+", message = VALID_EMAIL) String email) {}

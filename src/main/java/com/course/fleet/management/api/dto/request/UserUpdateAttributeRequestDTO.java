package com.course.fleet.management.api.dto.request;

import static com.course.fleet.management.api.constants.CustomErrorMessage.MANDATORY_EMAIL;
import static com.course.fleet.management.api.constants.CustomErrorMessage.MANDATORY_NAME;
import static com.course.fleet.management.api.constants.CustomErrorMessage.VALID_EMAIL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateAttributeRequestDTO(
    @NotBlank(message = MANDATORY_NAME) String name,
    @NotBlank(message = MANDATORY_EMAIL) @Email(regexp = ".+@.+\\..+", message = VALID_EMAIL)
        String email) {}

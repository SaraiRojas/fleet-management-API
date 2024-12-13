package com.course.fleet.management.api.utils

import com.course.fleet.management.api.domain.User
import com.course.fleet.management.api.dto.request.UserUpdateAttributeRequestDTO
import com.course.fleet.management.api.dto.request.UserUpdateRequestDTO
import com.course.fleet.management.api.entity.UserEntity

import static com.course.fleet.management.api.utils.UserMock.NAME
import static com.course.fleet.management.api.utils.UserMock.EMAIL
import static com.course.fleet.management.api.utils.UserMock.ID


class TestUtils {

    static UserUpdateRequestDTO buildUserUpdateRequestDTO() {
        return new UserUpdateRequestDTO(NAME, EMAIL);
    }

    static UserUpdateAttributeRequestDTO buildUserUpdateAttributeRequestDTO() {
        return new UserUpdateAttributeRequestDTO(NAME,EMAIL);
    }

    static User buildUser() {
        return User.builder()
            .name(NAME)
            .email(EMAIL)
            .id(ID)
            .build()
    }

    static UserEntity buildUserEntity() {
        return UserEntity.builder()
                .name(NAME)
                .email(EMAIL)
                .id(ID)
                .build()
    }

    static List<UserEntity> buildUserEntityList() {
        return Arrays.asList(buildUserEntity())
    }
}

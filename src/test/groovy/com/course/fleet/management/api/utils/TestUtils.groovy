package com.course.fleet.management.api.utils

import com.course.fleet.management.api.domain.Taxi
import com.course.fleet.management.api.domain.User
import com.course.fleet.management.api.dto.request.UserUpdateAttributeRequestDTO
import com.course.fleet.management.api.dto.request.UserUpdateRequestDTO
import com.course.fleet.management.api.entity.TaxiEntity
import com.course.fleet.management.api.entity.UserEntity

import static TestConstants.NAME
import static TestConstants.EMAIL
import static TestConstants.ID
import static TestConstants.PLATE


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

    static TaxiEntity buildTaxiEntity() {
        return TaxiEntity.builder()
                .id(ID)
                .plate(PLATE)
                .build()
    }

    static Taxi buildTaxi() {
        return Taxi.builder()
                .id(ID)
                .plate(PLATE)
                .build()
    }

    static List<TaxiEntity> buildTaxiEntityList() {
        return Arrays.asList(buildTaxiEntity())
    }


}

package com.course.fleet.management.api.utils

import com.course.fleet.management.api.domain.Taxi
import com.course.fleet.management.api.domain.Trajectory
import com.course.fleet.management.api.domain.User
import com.course.fleet.management.api.dto.request.UserCreateRequestDTO
import com.course.fleet.management.api.dto.request.UserUpdateAttributeRequestDTO
import com.course.fleet.management.api.dto.request.UserUpdateRequestDTO
import com.course.fleet.management.api.entity.TaxiEntity
import com.course.fleet.management.api.entity.TrajectoryEntity
import com.course.fleet.management.api.entity.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

import static TestConstants.NAME
import static TestConstants.EMAIL
import static TestConstants.ID
import static TestConstants.PLATE
import static TestConstants.LONGITUDE
import static TestConstants.LATITUDE
import static com.course.fleet.management.api.utils.TestConstants.DATE_START_DAY


class TestUtils {

    static UserCreateRequestDTO buildUserCreateRequestDTO() {
        return new UserCreateRequestDTO(NAME, EMAIL);
    }

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

    static List<User> buildUserList() {
        return Arrays.asList(buildUser())
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

    static List<Taxi> buildTaxiList() {
        return Arrays.asList(buildTaxi())
    }

    static List<TaxiEntity> buildTaxiEntityList() {
        return Arrays.asList(buildTaxiEntity())
    }

    static Trajectory buildTrajectory() {
        return Trajectory.builder()
                .id(ID)
                .date(DATE_START_DAY)
                .taxiId(ID)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build()
    }

    static List<Trajectory> buildTrajectoryList() {
        return Arrays.asList(buildTrajectory())
    }

    static TrajectoryEntity buildTrajectoryEntity() {
        return TrajectoryEntity.builder()
                .id(ID)
                .date(DATE_START_DAY)
                .taxiId(ID)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build()
    }

    static List<TrajectoryEntity> buildTrajectoryEntityList() {
        return Arrays.asList(buildTrajectoryEntity())
    }

    static Page<TrajectoryEntity> buildTrajectoryEntityPage() {
        return new PageImpl<>(buildTrajectoryEntityList(), TestConstants.PAGEABLE as Pageable, buildTrajectoryEntityList().size())
    }

    static Page<Trajectory> buildTrajectoryPage() {
        return new PageImpl<>(buildTrajectoryList(), TestConstants.PAGEABLE as Pageable, buildTrajectoryList().size())
    }
}

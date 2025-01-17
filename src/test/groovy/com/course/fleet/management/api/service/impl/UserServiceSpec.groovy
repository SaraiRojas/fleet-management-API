package com.course.fleet.management.api.service.impl


import com.course.fleet.management.api.repository.UserRepository
import com.course.fleet.management.api.utils.TestUtils
import com.course.fleet.management.api.utils.TestConstants
import spock.lang.Specification
import spock.lang.Subject

class UserServiceSpec extends Specification{

    def userRepository = Mock(UserRepository)

    @Subject
    def subject = new UserServiceImpl(userRepository)

    def "Create - should call once userRepository"() {
        given:
        def user = TestUtils.buildUser()
        def userEntity = TestUtils.buildUserEntity()

        when:
        subject.create(user)

        then:
        1 * userRepository.save({
            it.name == TestConstants.NAME
            it.email == TestConstants.EMAIL
            it.id == TestConstants.ID
        }) >> userEntity
        noExceptionThrown()
    }

    def "GetAll - should return a List"() {
        given:
        def userEntityList = TestUtils.buildUserEntityList()

        when:
        def users = subject.getAll()

        then:
        1 * userRepository.findAll() >> userEntityList
        users instanceof List
        noExceptionThrown()
    }

    def "GetUser - should return an user"() {
        given:
        def userEntity = TestUtils.buildUserEntity()

        when:
        subject.getUser(TestConstants.ID)

        then:
        1 * userRepository.findById(TestConstants.ID) >> Optional.of(userEntity)
        noExceptionThrown()
    }

    def "Update - should call once userRepository"() {
        given:
        def userUpdateDTO = TestUtils.buildUserUpdateRequestDTO()
        def userEntity = TestUtils.buildUserEntity()

        userRepository.findById(TestConstants.ID) >> Optional.of(userEntity)

        when:
        subject.updateUser(userUpdateDTO, TestConstants.ID)

        then:
        1 * userRepository.save({
            it.name == TestConstants.NAME
            it.email == TestConstants.EMAIL
            it.id == TestConstants.ID
        }) >> userEntity
        noExceptionThrown()
    }

    def "UpdateAttribute - should call once userRepository"() {
        given:
        def userUpdateAttributeDTO = TestUtils.buildUserUpdateAttributeRequestDTO()
        def userEntity = TestUtils.buildUserEntity()

        userRepository.findById(TestConstants.ID) >> Optional.of(userEntity)

        when:
        subject.updateUserAttribute(userUpdateAttributeDTO, TestConstants.ID)

        then:
        1 * userRepository.save({
            it.name == TestConstants.NAME
            it.email == TestConstants.EMAIL
            it.id == TestConstants.ID
        }) >> userEntity
        noExceptionThrown()
    }

    def "DeleteUser - should call once userRepository"() {
        given:
        def userEntity = TestUtils.buildUserEntity()
        userRepository.findById(TestConstants.ID) >> Optional.of(userEntity)

        when:
        subject.deleteUser(TestConstants.ID)

        then:
        1 * userRepository.deleteById(TestConstants.ID)
        noExceptionThrown()
    }
}

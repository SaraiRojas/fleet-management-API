package com.course.fleet.management.api.controller

import com.course.fleet.management.api.service.UserService
import com.course.fleet.management.api.utils.TestUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import com.course.fleet.management.api.mapper.UserMapper;
import spock.lang.Specification

import static com.course.fleet.management.api.utils.TestConstants.EMAIL
import static com.course.fleet.management.api.utils.TestConstants.NAME
import static com.course.fleet.management.api.utils.TestConstants.ID
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static com.course.fleet.management.api.constants.UriConstant.USER_BASE_URL
import static com.course.fleet.management.api.constants.UriConstant.USER_GET_ALL
import static com.course.fleet.management.api.constants.UriConstant.USER_BY_ID
import static com.course.fleet.management.api.constants.UriConstant.USER_UPDATE
import static com.course.fleet.management.api.constants.UriConstant.USER_UPDATE_ATTRIBUTE
import static com.course.fleet.management.api.constants.UriConstant.USER_DELETE
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE



@WebAppConfiguration
@SpringBootTest(classes = UserController)
class UserControllerSpec extends Specification{

    @SpringBean
    UserService userService = Mock(UserService)

    @Autowired
    UserController userController

    MockMvc mockMvc

    ObjectMapper objectMapper = new ObjectMapper()

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build()
    }

    def "create - should call userService.create once and return a 201 status code"() {
        given:
        def userCreateRequestDTO = TestUtils.buildUserCreateRequestDTO()
        def user = TestUtils.buildUser()

        when:
        def result = mockMvc.perform(post(USER_BASE_URL)
                                    .contentType(APPLICATION_JSON_VALUE)
                                    .content(objectMapper.writeValueAsString(userCreateRequestDTO)))

        then:
        1*userService.create({
            it.name == NAME
            it.email == EMAIL
        }) >> user
        result.andExpect(status().isCreated())
        noExceptionThrown()
    }

    def "getUsers - should call userService.getAll once and return 200 status"() {
        given:
        def userList = TestUtils.buildUserList()
        def userResponseDTOList = UserMapper.INSTANCE.toListUserResponseDTO(userList)

        when:
        def result = mockMvc.perform(get(USER_BASE_URL + USER_GET_ALL)
                .contentType(APPLICATION_JSON_VALUE))

        then:
        1 * userService.getAll() >> userList
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponseDTOList)))
        noExceptionThrown()
    }

    def "getUser - should call userService.getUser once and return 200 status"() {
        given:
        def user = TestUtils.buildUser()
        def userResponseDTO = UserMapper.INSTANCE.toUserResponseDTO(user)

        when:
        def result = mockMvc.perform(get(USER_BASE_URL + USER_BY_ID, user.getId())
                .contentType(APPLICATION_JSON_VALUE))

        then:
        1 * userService.getUser(user.getId()) >> user
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponseDTO)))
        noExceptionThrown()
    }

    def "updateUser - should call userService.updateUser once and return 200 status"() {
        given:
        def userUpdateRequestDTO = TestUtils.buildUserUpdateRequestDTO()
        def user = TestUtils.buildUser()
        def userUpdateResponseDTO = UserMapper.INSTANCE.toUserUpdateResponseDTO(user)

        when:
        def result = mockMvc.perform(put(USER_BASE_URL + USER_UPDATE, user.getId())
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userUpdateRequestDTO)))

        then:
        1 * userService.updateUser(userUpdateRequestDTO, user.getId()) >> user
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userUpdateResponseDTO)))
        noExceptionThrown()
    }

    def "updateUserAttribute - should call userService.updateUserAttribute once and return 200 status"() {
        given:
        def userUpdateAttributeRequestDTO = TestUtils.buildUserUpdateAttributeRequestDTO()
        def user = TestUtils.buildUser()
        def userUpdateAttributeResponseDTO = UserMapper.INSTANCE.toUserUpdateAttributeResponseDTO(user)

        when:
        def result = mockMvc.perform(patch(USER_BASE_URL + USER_UPDATE_ATTRIBUTE, user.getId())
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userUpdateAttributeRequestDTO)))

        then:
        1 * userService.updateUserAttribute(userUpdateAttributeRequestDTO, user.getId()) >> user
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userUpdateAttributeResponseDTO)))
        noExceptionThrown()
    }

    def "deleteUser - should call userService.deleteUser once and return 404 status"() {
        given:
        def id = ID

        when:
        def result = mockMvc.perform(delete(USER_BASE_URL + USER_DELETE, id)
                .contentType(APPLICATION_JSON_VALUE))

        then:
        1 * userService.deleteUser(id)
        result.andExpect(status().isNotFound())
        noExceptionThrown()
    }
}

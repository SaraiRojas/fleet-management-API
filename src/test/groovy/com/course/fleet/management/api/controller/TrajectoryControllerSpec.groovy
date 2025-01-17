package com.course.fleet.management.api.controller

import com.course.fleet.management.api.service.TrajectoryService
import com.course.fleet.management.api.utils.TestConstants
import com.course.fleet.management.api.utils.TestUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.BeforeEach
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import com.course.fleet.management.api.mapper.TrajectoryMapper
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static com.course.fleet.management.api.constants.UriConstant.TRAJECTORY_BASE_URL
import static com.course.fleet.management.api.constants.UriConstant.TAXI_TRAJECTORY
import static com.course.fleet.management.api.constants.UriConstant.LATEST_TRAJECTORIES
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static com.course.fleet.management.api.utils.TestConstants.DATE_STRING
import static com.course.fleet.management.api.utils.TestConstants.ID


@WebAppConfiguration
@SpringBootTest(classes = TrajectoryController)
class TrajectoryControllerSpec extends Specification {

    @SpringBean
    TrajectoryService trajectoryService = Mock(TrajectoryService)

    @Autowired
    TrajectoryController trajectoryController

    MockMvc mockMvc

    ObjectMapper objectMapper = new ObjectMapper()

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule())
        mockMvc = MockMvcBuilders.standaloneSetup(trajectoryController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build()
    }

    def "getTaxiTrajectoriesByTaxiIdAndDate - should call trajectoryService.getTaxiTrajectoryByDate once and return 200 status"() {
        given:
        def taxiId = ID
        def date = DATE_STRING
        def pageable = TestConstants.PAGEABLE as Pageable
        def trajectoryPage = TestUtils.buildTrajectoryPage()
        def trajectoryPageResponseDTO = TrajectoryMapper.INSTANCE.toTrajectoryResponseDTOPage(trajectoryPage, pageable)

        when:
        def result = mockMvc.perform(get(TRAJECTORY_BASE_URL + TAXI_TRAJECTORY, taxiId)
                .param("date", date)
                .param("page", String.valueOf(pageable.pageNumber))
                .param("size", String.valueOf(pageable.pageSize))
                .param("sort", pageable.sort.toString())
                .contentType(APPLICATION_JSON_VALUE))

        then:
        1 * trajectoryService.getTaxiTrajectoryByDate(taxiId, date, { it.pageNumber == 0 && it.pageSize == 10 && it.sort.isSorted() }) >> trajectoryPage
        result.andExpect(status().isOk())
        trajectoryPageResponseDTO instanceof Page
        noExceptionThrown()
    }

    def "getLatestTaxisTrajectories - should call trajectoryService.getLatestTaxisTrajectories once and return 200 status"() {
        given:
        def pageable = TestConstants.PAGEABLE as Pageable
        def trajectoryPage = TestUtils.buildTrajectoryPage()
        def trajectoryPageResponseDTO = TrajectoryMapper.INSTANCE.toTrajectoryResponseDTOPage(trajectoryPage, pageable)

        when:
        def result = mockMvc.perform(get(TRAJECTORY_BASE_URL + LATEST_TRAJECTORIES)
                .param("page", String.valueOf(pageable.pageNumber))
                .param("size", String.valueOf(pageable.pageSize))
                .param("sort", pageable.sort.toString())
                .contentType(APPLICATION_JSON_VALUE))

        then:
        1 * trajectoryService.getLatestTaxisTrajectories({ it.pageNumber == 0 && it.pageSize == 10 && it.sort.isSorted() }) >> trajectoryPage

        result.andExpect(status().isOk())
        trajectoryPageResponseDTO instanceof Page
        noExceptionThrown()
    }
}

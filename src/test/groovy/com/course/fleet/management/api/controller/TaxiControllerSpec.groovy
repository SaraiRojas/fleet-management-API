package com.course.fleet.management.api.controller

import com.course.fleet.management.api.service.TaxiService
import com.course.fleet.management.api.utils.TestUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import com.course.fleet.management.api.mapper.TaxiMapper
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static com.course.fleet.management.api.constants.UriConstant.TAXI_BASE_URL
import static com.course.fleet.management.api.constants.UriConstant.TAXI_GET_ALL
import static com.course.fleet.management.api.constants.UriConstant.TAXI_GET_BY_PLATE
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static com.course.fleet.management.api.utils.TestConstants.PLATE



@WebAppConfiguration
@SpringBootTest(classes = TaxiController)
class TaxiControllerSpec extends Specification {

    @SpringBean
    TaxiService taxiService = Mock(TaxiService)

    @Autowired
    TaxiController taxiController

    MockMvc mockMvc

    ObjectMapper objectMapper = new ObjectMapper()

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(taxiController).build()
    }

    def "getTaxis - should call taxiService.getAll once and return 200 status"() {
        given:
        def taxiList = TestUtils.buildTaxiList()
        def taxiListResponseDTO = TaxiMapper.INSTANCE.toListTaxiResponseDTO(taxiList)

        when:
        def result = mockMvc.perform(get(TAXI_BASE_URL + TAXI_GET_ALL)
                .contentType(APPLICATION_JSON_VALUE))

        then:
        1 * taxiService.getAll() >> taxiList
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(taxiListResponseDTO)))
        noExceptionThrown()
    }

    def "getTaxiByPlate - should call taxiService.getTaxiByPlate once and return 200 status"() {
        given:
        def plate = PLATE
        def taxi = TestUtils.buildTaxi()
        def taxiResponseDTO = TaxiMapper.INSTANCE.toTaxiResponseDTO(taxi)

        when:
        def result = mockMvc.perform(get(TAXI_BASE_URL + TAXI_GET_BY_PLATE, plate)
                .contentType(APPLICATION_JSON_VALUE))

        then:
        1 * taxiService.getTaxiByPlate(plate) >> taxi
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(taxiResponseDTO)))
        noExceptionThrown()
    }
}
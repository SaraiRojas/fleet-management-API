package com.course.fleet.management.api.service.impl

import com.course.fleet.management.api.utils.TestConstants
import com.course.fleet.management.api.repository.TaxiRepository
import com.course.fleet.management.api.utils.TestUtils
import spock.lang.Specification
import spock.lang.Subject

class TaxiServiceSpec extends Specification{

    def taxiRepository = Mock(TaxiRepository)

    @Subject
    def subject = new TaxiServiceImpl(taxiRepository)

    def "GetAll - should return a List"() {
        given:
        def taxiEntityList = TestUtils.buildTaxiEntityList()

        when:
        def taxis = subject.getAll()

        then:
        1 * taxiRepository.findAll() >> taxiEntityList
        taxis instanceof List
        noExceptionThrown()
    }

    def "GetTaxi - should return an user"() {
        given:
        def taxiEntity = TestUtils.buildTaxiEntity()

        when:
        subject.getTaxiByPlate(TestConstants.PLATE)

        then:
        1 * taxiRepository.findByPlate(TestConstants.PLATE) >> Optional.of(taxiEntity)
        noExceptionThrown()
    }
}

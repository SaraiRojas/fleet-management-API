package com.course.fleet.management.api.service.impl

import com.course.fleet.management.api.repository.TrajectoryRepository
import com.course.fleet.management.api.utils.TestConstants
import com.course.fleet.management.api.utils.TestUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import spock.lang.Specification
import spock.lang.Subject

class TrajectoryServiceSpec extends Specification{

    def trajectoryRepository = Mock(TrajectoryRepository)

    @Subject
    def subject = new TrajectoryServiceImpl(trajectoryRepository)

    def "getTaxiTrajectoryByDate - should return a pageable"() {
        given:
        def trajectoryEntityPage = TestUtils.buildTrajectoryEntityPage()

        when:
        def trajectories = subject.getTaxiTrajectoryByDate(TestConstants.ID, TestConstants.DATE_STRING, TestConstants.PAGEABLE as Pageable)

        then:
        1 * trajectoryRepository.findByTaxiIdAndDateBetween(TestConstants.ID, TestConstants.DATE_START_DAY, TestConstants.DATE_END_DAY, { it.pageNumber == 0 && it.pageSize == 10 && it.sort.isSorted() }) >> Optional.of(trajectoryEntityPage)
        trajectories instanceof Page
        noExceptionThrown()
    }

    def "getTaxiLatestTrajectories - should return a pageable"() {
        given:
        def trajectoryEntityPage = TestUtils.buildTrajectoryEntityPage()

        when:
        def trajectories = subject.getLatestTaxisTrajectories(TestConstants.PAGEABLE as Pageable)

        then:
        1 * trajectoryRepository.findLatestTrajectories({ it.pageNumber == 0 && it.pageSize == 10 && it.sort.isSorted() }) >> Optional.of(trajectoryEntityPage)
        trajectories instanceof Page
        noExceptionThrown()
    }
}

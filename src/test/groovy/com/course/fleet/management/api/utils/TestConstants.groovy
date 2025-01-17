package com.course.fleet.management.api.utils

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

import java.awt.print.Pageable
import java.time.LocalDateTime

interface TestConstants {
    static final String NAME = "Juli White"

    static final String EMAIL = "juli.white@mail.com"

    static final Long ID = 7658

    static final String PLATE = "wer-ty6"

    static final String DATE_STRING = "2008-02-02"

    static final LocalDateTime DATE_START_DAY = LocalDateTime.of(2008, 02, 02, 0, 0)

    static final LocalDateTime DATE_END_DAY = LocalDateTime.of(2008, 02, 02, 23, 59, 59)

    static final Double LATITUDE = 116.29121

    static final Double LONGITUDE = 9.9026

    static final Pageable PAGEABLE = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("someField"))) as Pageable
}

package com.course.fleet.management.api.dto.response;

import java.time.LocalDateTime;

public record TrajectoryResponseDTO(
    Long id, Long taxiId, LocalDateTime date, Double latitude, Double longitude) {}

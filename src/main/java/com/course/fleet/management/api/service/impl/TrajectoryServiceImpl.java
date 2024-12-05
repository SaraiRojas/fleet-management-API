package com.course.fleet.management.api.service.impl;

import static com.course.fleet.management.api.constants.CustomErrorMessage.LATEST_TRAJECTORIES_NOT_FOUND;
import static com.course.fleet.management.api.constants.CustomErrorMessage.TRAJECTORY_NOT_FOUND;
import static com.course.fleet.management.api.util.DateTimeUtils.getDateAtEndOfDay;
import static com.course.fleet.management.api.util.DateTimeUtils.getDateAtStartOfDay;

import com.course.fleet.management.api.domain.Trajectory;
import com.course.fleet.management.api.entity.TrajectoryEntity;
import com.course.fleet.management.api.exception.customExceptions.TrajectoryNotFoundException;
import com.course.fleet.management.api.mapper.TrajectoryMapper;
import com.course.fleet.management.api.repository.TrajectoryRepository;
import com.course.fleet.management.api.service.TrajectoryService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrajectoryServiceImpl implements TrajectoryService {

  private final TrajectoryRepository trajectoryRepository;

  @Override
  public Page<Trajectory> getTaxiTrajectoryByDate(Long taxiId, String date, Pageable pageable) {

    LocalDateTime dateTimeStartOfDay = getDateAtStartOfDay(date);
    LocalDateTime dateTimeEndOfDay = getDateAtEndOfDay(date);

    final Page<TrajectoryEntity> trajectoryEntities =
        trajectoryRepository
            .findByTaxiIdAndDateBetween(taxiId, dateTimeStartOfDay, dateTimeEndOfDay, pageable)
            .orElseThrow(() -> new TrajectoryNotFoundException(TRAJECTORY_NOT_FOUND));

    if (trajectoryEntities.isEmpty()) {
      throw new TrajectoryNotFoundException(TRAJECTORY_NOT_FOUND);
    }

    return TrajectoryMapper.INSTANCE.toTrajectoryPage(trajectoryEntities, pageable);
  }

  @Override
  public Page<Trajectory> getLatestTaxisTrajectories(Pageable pageable) {

    final Page<TrajectoryEntity> trajectoryEntities =
        trajectoryRepository
            .findLatestTrajectories(pageable)
            .orElseThrow(() -> new TrajectoryNotFoundException(LATEST_TRAJECTORIES_NOT_FOUND));

    if (trajectoryEntities.isEmpty()) {
      throw new TrajectoryNotFoundException(LATEST_TRAJECTORIES_NOT_FOUND);
    }

    return TrajectoryMapper.INSTANCE.toTrajectoryPage(trajectoryEntities, pageable);
  }
}

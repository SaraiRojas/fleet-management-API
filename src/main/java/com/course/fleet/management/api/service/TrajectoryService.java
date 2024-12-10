package com.course.fleet.management.api.service;

import com.course.fleet.management.api.domain.Trajectory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrajectoryService {

  Page<Trajectory> getTaxiTrajectoryByDate(Long taxiId, String date, Pageable pageable);

  Page<Trajectory> getLatestTaxisTrajectories(Pageable pageable);
}

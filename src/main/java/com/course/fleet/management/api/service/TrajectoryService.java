package com.course.fleet.management.api.service;

import com.course.fleet.management.api.domain.Trajectory;
import java.util.List;

public interface TrajectoryService {

  List<Trajectory> getTaxiTrajectoryByDate(Long taxiId, String date);

  List<Trajectory> getLatestTaxisTrajectories();
}

package com.course.fleet.management.api.controller;

import static com.course.fleet.management.api.constants.UriConstant.LATEST_TRAJECTORIES;
import static com.course.fleet.management.api.constants.UriConstant.TAXI_TRAJECTORY;
import static com.course.fleet.management.api.constants.UriConstant.TRAJECTORY_BASE_URL;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.course.fleet.management.api.domain.Trajectory;
import com.course.fleet.management.api.dto.response.TrajectoryResponseDTO;
import com.course.fleet.management.api.mapper.TrajectoryMapper;
import com.course.fleet.management.api.service.TrajectoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = TRAJECTORY_BASE_URL)
public class TrajectoryController {

  private final TrajectoryService trajectoryService;

  @GetMapping(value = TAXI_TRAJECTORY, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<TrajectoryResponseDTO>> getTaxiTrajectoriesByTaxiIdAndDate(
      @PathVariable Long taxiId, @RequestParam String date, Pageable pageable) {

    Page<Trajectory> trajectoryPage =
        trajectoryService.getTaxiTrajectoryByDate(taxiId, date, pageable);
    Page<TrajectoryResponseDTO> trajectoryPageResponseDTO =
        TrajectoryMapper.INSTANCE.toTrajectoryResponseDTOPage(trajectoryPage, pageable);
    return trajectoryPageResponseDTO.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(trajectoryPageResponseDTO);
  }

  @GetMapping(value = LATEST_TRAJECTORIES, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<TrajectoryResponseDTO>> getLatestTaxisTrajectories(Pageable pageable) {

    Page<Trajectory> trajectoriesPage = trajectoryService.getLatestTaxisTrajectories(pageable);
    Page<TrajectoryResponseDTO> trajectoryPageResponseDTO =
        TrajectoryMapper.INSTANCE.toTrajectoryResponseDTOPage(trajectoriesPage, pageable);
    return trajectoryPageResponseDTO.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(trajectoryPageResponseDTO);
  }
}

package com.course.fleet.management.api.mapper;

import com.course.fleet.management.api.domain.Trajectory;
import com.course.fleet.management.api.dto.response.TrajectoryResponseDTO;
import com.course.fleet.management.api.entity.TrajectoryEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrajectoryMapper {

  TrajectoryMapper INSTANCE = Mappers.getMapper(TrajectoryMapper.class);

  TrajectoryResponseDTO toTrajectoryResponseDTO(Trajectory trajectory);

  List<TrajectoryResponseDTO> toListTrajectoryResponseDTO(List<Trajectory> trajectoriesList);

  Trajectory toTrajectory(TrajectoryEntity trajectoryEntity);

  default List<Trajectory> toTrajectoryList(List<TrajectoryEntity> trajectoryEntities) {
    return trajectoryEntities.stream().map(this::toTrajectory).collect(Collectors.toList());
  }
}

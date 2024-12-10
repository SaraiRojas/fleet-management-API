package com.course.fleet.management.api.mapper;

import com.course.fleet.management.api.domain.Trajectory;
import com.course.fleet.management.api.dto.response.TrajectoryResponseDTO;
import com.course.fleet.management.api.entity.TrajectoryEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Mapper
public interface TrajectoryMapper {

  TrajectoryMapper INSTANCE = Mappers.getMapper(TrajectoryMapper.class);

  TrajectoryResponseDTO toTrajectoryResponseDTO(Trajectory trajectory);

  List<TrajectoryResponseDTO> toListTrajectoryResponseDTO(List<Trajectory> trajectoryList);

  Trajectory toTrajectory(TrajectoryEntity trajectoryEntity);

  default List<Trajectory> toTrajectoryList(List<TrajectoryEntity> trajectoryEntities) {
    return trajectoryEntities.stream().map(this::toTrajectory).collect(Collectors.toList());
  }

  default Page<Trajectory> toTrajectoryPage(
      Page<TrajectoryEntity> trajectoryEntityPage, Pageable pageable) {
    final List<Trajectory> trajectoryList =
        toTrajectoryList(trajectoryEntityPage.getContent().stream().toList());

    return new PageImpl<>(trajectoryList, pageable, trajectoryEntityPage.getTotalElements());
  }

  default Page<TrajectoryResponseDTO> toTrajectoryResponseDTOPage(
      Page<Trajectory> trajectoryPage, Pageable pageable) {
    final List<TrajectoryResponseDTO> trajectoryList =
        toListTrajectoryResponseDTO(trajectoryPage.getContent().stream().toList());

    return new PageImpl<>(trajectoryList, pageable, trajectoryPage.getTotalElements());
  }
  ;
}

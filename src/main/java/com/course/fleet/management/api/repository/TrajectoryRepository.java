package com.course.fleet.management.api.repository;

import com.course.fleet.management.api.entity.TrajectoryEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajectoryRepository extends JpaRepository<TrajectoryEntity, Long> {

  Optional<Page<TrajectoryEntity>> findByTaxiIdAndDateBetween(
      Long taxiId, LocalDateTime dateFrom, LocalDateTime dateTo, Pageable pageable);

  @Query(
      value =
          """
        WITH latest_trajectories AS (
            SELECT *, ROW_NUMBER() OVER (PARTITION BY taxiid ORDER BY date DESC) AS rn
            FROM trajectories
        )
        SELECT *
        FROM latest_trajectories
        WHERE rn = 1;
        """,
      countQuery =
          """
        WITH latest_trajectories AS (
            SELECT DISTINCT taxiid
            FROM trajectories
        )
        SELECT COUNT(*) FROM latest_trajectories
        """,
      nativeQuery = true)
  Optional<Page<TrajectoryEntity>> findLatestTrajectories(Pageable pageable);
}

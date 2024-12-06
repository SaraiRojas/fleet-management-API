package com.course.fleet.management.api.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trajectories")
public class TrajectoryEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "taxiid", nullable = false)
  private Long taxiId;

  @Column(nullable = false)
  private LocalDateTime date;

  @Column(nullable = false)
  private Double latitude;

  @Column(nullable = false)
  private Double longitude;
}

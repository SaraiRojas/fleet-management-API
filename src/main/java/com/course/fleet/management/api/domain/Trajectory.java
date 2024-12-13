package com.course.fleet.management.api.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trajectory {
  private Long id;
  private Long taxiId;
  private LocalDateTime date;
  private Double latitude;
  private Double longitude;
}

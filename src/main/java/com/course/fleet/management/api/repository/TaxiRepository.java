package com.course.fleet.management.api.repository;

import com.course.fleet.management.api.entity.TaxiEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<TaxiEntity, Long> {

  Optional<TaxiEntity> findByPlate(String plate);
}

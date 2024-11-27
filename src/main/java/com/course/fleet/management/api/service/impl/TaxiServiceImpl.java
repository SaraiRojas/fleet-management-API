package com.course.fleet.management.api.service.impl;

import static com.course.fleet.management.api.constants.CustomErrorMessage.TAXI_NOT_FOUND;

import com.course.fleet.management.api.domain.Taxi;
import com.course.fleet.management.api.entity.TaxiEntity;
import com.course.fleet.management.api.exception.customExceptions.UserNotFoundException;
import com.course.fleet.management.api.mapper.TaxiMapper;
import com.course.fleet.management.api.repository.TaxiRepository;
import com.course.fleet.management.api.service.TaxiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaxiServiceImpl implements TaxiService {

  private final TaxiRepository taxiRepository;

  @Override
  public List<Taxi> getAll() {
    final List<TaxiEntity> taxiEntities = taxiRepository.findAll();
    return TaxiMapper.INSTANCE.toTaxiList(taxiEntities);
  }

  @Override
  public Taxi getTaxiByPlate(String plate) {
    return findByPlate(plate);
  }

  private Taxi findByPlate(String plate) {
    TaxiEntity taxiEntity =
        taxiRepository
            .findByPlate(plate)
            .orElseThrow(() -> new UserNotFoundException(TAXI_NOT_FOUND));

    return TaxiMapper.INSTANCE.toTaxi(taxiEntity);
  }
}

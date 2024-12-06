package com.course.fleet.management.api.controller;

import static com.course.fleet.management.api.constants.UriConstant.TAXI_BASE_URL;
import static com.course.fleet.management.api.constants.UriConstant.TAXI_GET_ALL;
import static com.course.fleet.management.api.constants.UriConstant.TAXI_GET_BY_PLATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.course.fleet.management.api.domain.Taxi;
import com.course.fleet.management.api.dto.response.TaxiResponseDTO;
import com.course.fleet.management.api.mapper.TaxiMapper;
import com.course.fleet.management.api.service.TaxiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = TAXI_BASE_URL)
public class TaxiController {

  private final TaxiService taxiService;

  @GetMapping(value = TAXI_GET_ALL, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TaxiResponseDTO>> getTaxis() {
    final List<Taxi> taxiList = taxiService.getAll();
    final List<TaxiResponseDTO> taxiListResponseDTO =
        TaxiMapper.INSTANCE.toListTaxiResponseDTO(taxiList);
    return ResponseEntity.ok(taxiListResponseDTO);
  }

  @GetMapping(value = TAXI_GET_BY_PLATE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<TaxiResponseDTO> getTaxiByPlate(@PathVariable String plate) {
    final Taxi taxi = taxiService.getTaxiByPlate(plate);
    final TaxiResponseDTO taxiResponseDTO = TaxiMapper.INSTANCE.toTaxiResponseDTO(taxi);
    return ResponseEntity.ok(taxiResponseDTO);
  }
}

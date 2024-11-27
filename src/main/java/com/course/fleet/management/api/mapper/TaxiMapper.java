package com.course.fleet.management.api.mapper;

import com.course.fleet.management.api.domain.Taxi;
import com.course.fleet.management.api.dto.response.TaxiResponseDTO;
import com.course.fleet.management.api.entity.TaxiEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaxiMapper {

  TaxiMapper INSTANCE = Mappers.getMapper(TaxiMapper.class);

  TaxiResponseDTO toTaxiResponseDTO(Taxi taxi);

  List<TaxiResponseDTO> toListTaxiResponseDTO(List<Taxi> taxiList);

  Taxi toTaxi(TaxiEntity taxiEntity);

  default List<Taxi> toTaxiList(List<TaxiEntity> taxiEntities) {
    return taxiEntities.stream().map(this::toTaxi).collect(Collectors.toList());
  }
}

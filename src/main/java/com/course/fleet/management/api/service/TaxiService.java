package com.course.fleet.management.api.service;

import com.course.fleet.management.api.domain.Taxi;
import java.util.List;

public interface TaxiService {

  List<Taxi> getAll();

  Taxi getTaxiByPlate(String plate);
}

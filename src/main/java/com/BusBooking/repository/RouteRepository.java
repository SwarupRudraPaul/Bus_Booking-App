package com.BusBooking.repository;

import com.BusBooking.entity.Route;
import com.BusBooking.payload.RouteDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route,Long> {
    List<Route> findByFromLocationAndToLocationAndDate(String fromLocation, String toLocation, String date);

    Optional<Route> findByBus_BusId(long busId);
}

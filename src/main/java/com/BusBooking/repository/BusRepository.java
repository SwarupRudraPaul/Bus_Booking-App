package com.BusBooking.repository;

import com.BusBooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {

    Optional<Bus> findByBusNumber(String busNumber);

    List<Bus> findByRouteRouteId(Long routeId);

    List<Bus> findBySubRouteSubRouteId(Long subRouteId);
}

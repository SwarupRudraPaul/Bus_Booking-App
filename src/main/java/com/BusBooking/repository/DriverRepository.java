package com.BusBooking.repository;

import com.BusBooking.entity.Bus;
import com.BusBooking.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {

    Driver findByBus(Bus bus);
}

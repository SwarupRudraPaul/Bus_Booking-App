package com.BusBooking.controller;

import com.BusBooking.payload.DriverDTO;
import com.BusBooking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/add")
    public ResponseEntity<DriverDTO> addDriver(@RequestBody DriverDTO driverDTO) {
        DriverDTO addedDriver = driverService.addDriver(driverDTO);
        return new ResponseEntity<>(addedDriver, HttpStatus.CREATED);
    }

    @GetMapping("/bus/{busId}")
    public DriverDTO findDriverByBusId(@PathVariable Long busId) {
        return driverService.findDriverByBusId(busId);
    }
}


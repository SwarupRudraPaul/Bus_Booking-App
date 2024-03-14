package com.BusBooking.controller;

import com.BusBooking.entity.Passenger;
import com.BusBooking.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/save")
    public ResponseEntity<String> savePassenger(@RequestBody Passenger passenger,
                                                @RequestParam long busId,
                                                @RequestParam long routeId,
                                                @RequestParam long subRouteId) {
        try {
            passengerService.savePassengerDetails(passenger, busId, routeId, subRouteId);
            return new ResponseEntity<>("Passenger details saved successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

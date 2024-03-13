package com.BusBooking.controller;

import com.BusBooking.exception.ResourceNotFoundException;
import com.BusBooking.payload.BusAndRouteDTO;
import com.BusBooking.payload.BusAndSubRouteDTO;
import com.BusBooking.payload.BusDTO;
import com.BusBooking.payload.RouteDTO;
import com.BusBooking.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/add")
    public ResponseEntity<BusDTO> addBus(@RequestBody BusDTO busDTO) {
        BusDTO addedBus = busService.addBus(busDTO);
        return new ResponseEntity<>(addedBus, HttpStatus.CREATED);
    }

    @GetMapping("/{busId}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable Long busId) {
        BusDTO busById = busService.getBusById(busId);
        return new ResponseEntity<>(busById, HttpStatus.OK);
    }

    @GetMapping("/busNumber/{busNumber}")
    public ResponseEntity<BusDTO> getBusByBusNumber(@PathVariable String busNumber) {
        BusDTO busByBusNumber = busService.getBusByBusNumber(busNumber);
        return new ResponseEntity<>(busByBusNumber, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Object>> findBusesByLocationAndDateOrSubRouteAndDate(
            @RequestParam String fromLocation,
            @RequestParam String toLocation,
            @RequestParam String date
    ) {
        try {
            List<Object> busesAndRoutes = busService.findBusesByLocationAndDateOrSubRouteAndDate(fromLocation, toLocation, date);
            return new ResponseEntity<>(busesAndRoutes, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


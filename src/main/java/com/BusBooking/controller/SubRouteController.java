package com.BusBooking.controller;

import com.BusBooking.payload.SubRouteDTO;
import com.BusBooking.service.SubRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subroutes")
public class SubRouteController {

    @Autowired
    private SubRouteService subRouteService;

    @PostMapping("/add")
    public ResponseEntity<SubRouteDTO> addSubRoute(@RequestBody SubRouteDTO subRouteDTO) {
        SubRouteDTO addedSubRoute = subRouteService.addSubRoute(subRouteDTO);
        return new ResponseEntity<>(addedSubRoute, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<SubRouteDTO>> getSubRoutesByLocationAndDate(@RequestParam String fromLocation,
                                                                           @RequestParam String toLocation,
                                                                           @RequestParam String date) {
        List<SubRouteDTO> subRoutes = subRouteService.getSubRoutesByLocationAndDate(fromLocation, toLocation, date);
        return new ResponseEntity<>(subRoutes, HttpStatus.OK);
    }

    // You can add more endpoints for other subroute-related operations as needed
}


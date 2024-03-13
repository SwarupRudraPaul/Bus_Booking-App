package com.BusBooking.controller;

import com.BusBooking.exception.NotFoundException;
import com.BusBooking.payload.RouteDTO;
import com.BusBooking.payload.SubRouteDTO;
import com.BusBooking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/add")
    public ResponseEntity<RouteDTO> addRoute(@RequestBody RouteDTO routeDTO) {
        RouteDTO addedRoute = routeService.addRoute(routeDTO);
        return new ResponseEntity<>(addedRoute, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<RouteDTO> getRouteDetailsByLocationsAndDate(@RequestParam String fromLocation,
                                                                      @RequestParam String toLocation,
                                                                      @RequestParam String date) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setFromLocation(fromLocation);
        routeDTO.setToLocation(toLocation);
        routeDTO.setDate(date);
        RouteDTO routeDetails = routeService.getRouteDetailsByLocationsAndDate(routeDTO);
        if (routeDetails != null) {
            return new ResponseEntity<>(routeDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{busId}")
    public ResponseEntity<RouteDTO> getRouteByBusId(@PathVariable long busId){
        RouteDTO routeByBusId = routeService.getRouteByBusId(busId);
        return new ResponseEntity<>(routeByBusId, HttpStatus.OK);
    }

    @GetMapping("/subroutes/{routeId}")
    public ResponseEntity<List<SubRouteDTO>> getSubRoutesByRouteId(@PathVariable long routeId) throws NotFoundException {
        List<SubRouteDTO> subRoutes = routeService.getSubRoutesByRouteId(routeId);
        return new ResponseEntity<>(subRoutes, HttpStatus.OK);
    }

    // You can add more endpoints for other route-related operations as needed
}


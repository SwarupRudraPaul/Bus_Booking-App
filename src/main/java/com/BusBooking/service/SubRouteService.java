package com.BusBooking.service;

import com.BusBooking.entity.Bus;
import com.BusBooking.entity.Route;
import com.BusBooking.entity.SubRoute;
import com.BusBooking.payload.SubRouteDTO;
import com.BusBooking.repository.BusRepository;
import com.BusBooking.repository.RouteRepository;
import com.BusBooking.repository.SubRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubRouteService {

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;

    public SubRouteDTO addSubRoute(SubRouteDTO subRouteDTO) {
        SubRoute subRoute = convertToEntity(subRouteDTO);
        SubRoute savedSubRoute = subRouteRepository.save(subRoute);
        return convertToDTO(savedSubRoute);
    }

    public List<SubRouteDTO> getSubRoutesByLocationAndDate(String fromLocation, String toLocation, String date) {
        // Query the database for subroutes matching the provided criteria
        List<SubRoute> subRoutes = subRouteRepository.findByFromLocationAndToLocationAndDate(fromLocation, toLocation, date);

        // Convert the retrieved SubRoute entities into DTOs
        return subRoutes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SubRoute convertToEntity(SubRouteDTO subRouteDTO) {
        SubRoute subRoute = new SubRoute();
        subRoute.setFromLocation(subRouteDTO.getFromLocation());
        subRoute.setToLocation(subRouteDTO.getToLocation());
        subRoute.setDepartureTime(subRouteDTO.getDepartureTime());
        subRoute.setArrivalTime(subRouteDTO.getArrivalTime());
        subRoute.setDuration(subRouteDTO.getDuration());
        subRoute.setDate(subRouteDTO.getDate());

        Route route = routeRepository.findById(subRouteDTO.getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("Route not found"));
        subRoute.setRoute(route);

        Bus bus = busRepository.findById(subRouteDTO.getBusId()).orElseThrow(() -> new IllegalArgumentException("Bus not found"));
        subRoute.setBus(bus);
        return subRoute;
    }

    private SubRouteDTO convertToDTO(SubRoute subRoute) {
        SubRouteDTO subRouteDTO = new SubRouteDTO();
        subRouteDTO.setSubRouteId(subRoute.getSubRouteId());
        subRouteDTO.setFromLocation(subRoute.getFromLocation());
        subRouteDTO.setToLocation(subRoute.getToLocation());
        subRouteDTO.setDepartureTime(subRoute.getDepartureTime());
        subRouteDTO.setArrivalTime(subRoute.getArrivalTime());
        subRouteDTO.setDuration(subRoute.getDuration());
        subRouteDTO.setDate(subRoute.getDate());
        subRouteDTO.setRouteId(subRoute.getRoute().getRouteId());
        subRouteDTO.setBusId(subRoute.getBus().getBusId());
        return subRouteDTO;
    }
}


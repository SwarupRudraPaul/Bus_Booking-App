package com.BusBooking.service;

import com.BusBooking.entity.Route;
import com.BusBooking.entity.SubRoute;
import com.BusBooking.exception.NotFoundException;
import com.BusBooking.payload.RouteDTO;
import com.BusBooking.payload.SubRouteDTO;
import com.BusBooking.repository.BusRepository;
import com.BusBooking.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;

    public RouteDTO addRoute(RouteDTO routeDTO) {
        Route route = convertToEntity(routeDTO);
        Route savedRoute = routeRepository.save(route);
        return convertToDTO(savedRoute);
    }

    public RouteDTO getRouteDetailsByLocationsAndDate(RouteDTO routeDTO) {
        // Query the repository to find routes based on fromLocation, toLocation, and date
        List<Route> routes = routeRepository.findByFromLocationAndToLocationAndDate(
                routeDTO.getFromLocation(), routeDTO.getToLocation(), routeDTO.getDate());

        // Convert the list of entities to a list of DTOs
        List<RouteDTO> routeDTOs = routes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        // Return the first route DTO found (assuming unique routes for the given parameters)
        return routeDTOs.isEmpty() ? null : routeDTOs.get(0);
    }

    public RouteDTO getRouteByBusId(long busId){
        Optional<Route> getByBusId = routeRepository.findByBus_BusId(busId);

        if (getByBusId.isPresent()){
            return convertToDTO(getByBusId.get());
        }else {
            throw new IllegalArgumentException("No Route Found For the Id:"+busId);
        }
    }

    public List<SubRouteDTO> getSubRoutesByRouteId(long routeId) throws NotFoundException {
        // Retrieve the Route entity from the database using the provided routeId
        Optional<Route> optionalRoute = routeRepository.findById(routeId);

        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();

            // Retrieve subroutes associated with this route
            List<SubRoute> subRoutes = route.getSubRoute();

            // Convert the retrieved SubRoute entities into DTOs
            return subRoutes.stream()
                    .map(this::convertSubRouteToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("Route not found for routeId: " + routeId);
        }
    }

    // Helper method to convert SubRoute entity to SubRouteDTO
    private SubRouteDTO convertSubRouteToDTO(SubRoute subRoute) {
        SubRouteDTO subRouteDTO = new SubRouteDTO();
        subRouteDTO.setSubRouteId(subRoute.getSubRouteId());
        subRouteDTO.setFromLocation(subRoute.getFromLocation());
        subRouteDTO.setToLocation(subRoute.getToLocation());
        subRouteDTO.setDepartureTime(subRoute.getDepartureTime());
        subRouteDTO.setArrivalTime(subRoute.getArrivalTime());
        subRouteDTO.setDate(subRoute.getDate());
        subRouteDTO.setDuration(subRoute.getDuration());
        // Populate subRouteDTO attributes from subRoute entity
        return subRouteDTO;
    }


    private Route convertToEntity(RouteDTO routeDTO) {
        Route route = new Route();
        route.setFromLocation(routeDTO.getFromLocation());
        route.setToLocation(routeDTO.getToLocation());
        route.setDepartureTime(routeDTO.getDepartureTime());
        route.setArrivalTime(routeDTO.getArrivalTime());
        route.setDuration(routeDTO.getDuration());
        route.setDate(routeDTO.getDate());

        // Assuming the property name for bus identifier in Bus entity is busId
        route.setBus(busRepository.findById(routeDTO.getBusId()).orElse(null));
        return route;
    }


    private RouteDTO convertToDTO(Route route) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setRouteId(route.getRouteId());
        routeDTO.setFromLocation(route.getFromLocation());
        routeDTO.setToLocation(route.getToLocation());
        routeDTO.setDepartureTime(route.getDepartureTime());
        routeDTO.setArrivalTime(route.getArrivalTime());
        routeDTO.setDuration(route.getDuration());
        routeDTO.setDate(route.getDate());
        // If busId is needed in RouteDTO
        routeDTO.setBusId(route.getBus().getBusId());
        return routeDTO;
    }
}


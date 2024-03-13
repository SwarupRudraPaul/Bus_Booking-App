package com.BusBooking.service;

import com.BusBooking.entity.Bus;
import com.BusBooking.entity.Route;
import com.BusBooking.entity.SubRoute;
import com.BusBooking.exception.BusNotFoundException;
import com.BusBooking.exception.ResourceNotFoundException;
import com.BusBooking.payload.BusAndRouteDTO;
import com.BusBooking.payload.BusAndSubRouteDTO;
import com.BusBooking.payload.BusDTO;
import com.BusBooking.repository.BusRepository;
import com.BusBooking.repository.RouteRepository;
import com.BusBooking.repository.SubRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository; // Assuming you have a repository for Bus entities

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

    public BusDTO addBus(BusDTO busDTO) {
        Bus bus = convertToEntity(busDTO);
        Bus savedBus = busRepository.save(bus);
        return convertToDTO(savedBus);
    }

    public BusDTO getBusById(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new BusNotFoundException("Bus with ID " + busId + " not found"));
        return convertToDTO(bus);
    }

    public BusDTO getBusByBusNumber(String busNumber) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new BusNotFoundException("Bus with number " + busNumber + " not found"));
        return convertToDTO(bus);
    }

    public List<Object> findBusesByLocationAndDateOrSubRouteAndDate(String fromLocation, String toLocation, String date) throws ResourceNotFoundException {
        List<Object> busesAndRoutes = new ArrayList<>();

        // Check if routes are present
        List<Route> routes = routeRepository.findByFromLocationAndToLocationAndDate(fromLocation, toLocation, date);
        if (!routes.isEmpty()) {
            // Routes found, search for buses for each route
            for (Route route : routes) {
                List<Bus> busesForRouteAndDate = busRepository.findByRouteRouteId(route.getRouteId());
                for (Bus bus : busesForRouteAndDate) {
                    busesAndRoutes.add(convertToDTO(bus, route));
                }
            }
            return busesAndRoutes;
        }

        // Routes not found, search for sub-routes
        List<SubRoute> subRoutes = subRouteRepository.findByFromLocationAndToLocationAndDate(fromLocation, toLocation, date);
        if (!subRoutes.isEmpty()) {
            // Sub-routes found, search for buses for each sub-route
            for (SubRoute subRoute : subRoutes) {
                List<Bus> busesForSubRouteAndDate = busRepository.findBySubRouteSubRouteId(subRoute.getSubRouteId());
                for (Bus bus : busesForSubRouteAndDate) {
                    busesAndRoutes.add(convertToDTO(bus, subRoute));
                }
            }
            return busesAndRoutes;
        }

        // Neither routes nor sub-routes found
        throw new ResourceNotFoundException("No routes or sub-routes found for the given criteria");
    }
    private BusAndSubRouteDTO convertToDTO(Bus bus, SubRoute subRoute) {
        BusAndSubRouteDTO dto = new BusAndSubRouteDTO();
        dto.setBusId(bus.getBusId());
        dto.setBusNumber(bus.getBusNumber());
        dto.setBusType(bus.getBusType());
        dto.setPrice(bus.getPrice());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setAvailableSeats(bus.getAvailableSeats());

        dto.setSubRouteId(subRoute.getSubRouteId());
        dto.setFromLocation(subRoute.getFromLocation());
        dto.setToLocation(subRoute.getToLocation());
        dto.setDepartureTime(subRoute.getDepartureTime());
        dto.setArrivalTime(subRoute.getArrivalTime());
        dto.setDuration(subRoute.getDuration());
        dto.setDate(subRoute.getDate());
        return dto;
    }

    private BusAndRouteDTO convertToDTO(Bus bus, Route route) {
        BusAndRouteDTO busAndRouteDTO = new BusAndRouteDTO();
        // Set BusDTO properties
        busAndRouteDTO.setBusId(bus.getBusId());
        busAndRouteDTO.setBusNumber(bus.getBusNumber());
        busAndRouteDTO.setBusType(bus.getBusType());
        busAndRouteDTO.setPrice(bus.getPrice());
        busAndRouteDTO.setTotalSeats(bus.getTotalSeats());
        busAndRouteDTO.setAvailableSeats(bus.getAvailableSeats());
        // Set RouteDTO properties
        busAndRouteDTO.setRouteId(route.getRouteId());
        busAndRouteDTO.setFromLocation(route.getFromLocation());
        busAndRouteDTO.setToLocation(route.getToLocation());
        busAndRouteDTO.setDepartureTime(route.getDepartureTime());
        busAndRouteDTO.setArrivalTime(route.getArrivalTime());
        busAndRouteDTO.setDuration(route.getDuration());
        busAndRouteDTO.setDate(route.getDate());
        return busAndRouteDTO;
    }


    private Bus convertToEntity(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setBusId(busDTO.getBusId());
        bus.setBusNumber(busDTO.getBusNumber());
        bus.setBusType(busDTO.getBusType());
        bus.setPrice(busDTO.getPrice());
        bus.setTotalSeats(busDTO.getTotalSeats());
        bus.setAvailableSeats(busDTO.getAvailableSeats());
        return bus;
    }

    private BusDTO convertToDTO(Bus bus) {
        BusDTO busDTO = new BusDTO();
        busDTO.setBusId(bus.getBusId());
        busDTO.setBusNumber(bus.getBusNumber());
        busDTO.setBusType(bus.getBusType());
        busDTO.setPrice(bus.getPrice());
        busDTO.setTotalSeats(bus.getTotalSeats());
        busDTO.setAvailableSeats(bus.getAvailableSeats());
        return busDTO;
    }
}



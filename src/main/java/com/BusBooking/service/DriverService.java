package com.BusBooking.service;

import com.BusBooking.entity.Driver;
import com.BusBooking.entity.Bus; // Import Bus entity
import com.BusBooking.exception.BusNotFoundException;
import com.BusBooking.exception.DriverNotFoundException;
import com.BusBooking.payload.DriverDTO;
import com.BusBooking.repository.DriverRepository;
import com.BusBooking.repository.BusRepository; // Import BusRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private BusRepository busRepository; // Inject BusRepository

    public DriverDTO addDriver(DriverDTO driverDTO) {
        Driver driver = convertToEntity(driverDTO);
        Driver savedDriver = driverRepository.save(driver);
        return convertToDTO(savedDriver);
    }

    public DriverDTO findDriverByBusId(Long busId) throws DriverNotFoundException {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new BusNotFoundException("Bus with ID " + busId + " not found"));

        Driver driver = driverRepository.findByBus(bus);
        if (driver == null) {
            throw new DriverNotFoundException("Driver for bus with ID " + busId + " not found");
        }

        return convertToDTO(driver);
    }

    private Driver convertToEntity(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setDriverId(driverDTO.getDriverId());
        driver.setName(driverDTO.getName());
        driver.setEmail(driverDTO.getEmail());
        driver.setPhone(driverDTO.getPhone());
        driver.setDrivingLicenseNumber(driverDTO.getDrivingLicenseNumber());

        // Set Bus entity by fetching it from the database using busId
        if (driverDTO.getBusId() != null) {
            Bus bus = busRepository.findById(driverDTO.getBusId()).orElse(null);
            driver.setBus(bus);
        }

        return driver;
    }

    private DriverDTO convertToDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setDriverId(driver.getDriverId());
        driverDTO.setName(driver.getName());
        driverDTO.setEmail(driver.getEmail());
        driverDTO.setPhone(driver.getPhone());
        driverDTO.setDrivingLicenseNumber(driver.getDrivingLicenseNumber());

        // Set busId in DriverDTO
        if (driver.getBus() != null) {
            driverDTO.setBusId(driver.getBus().getBusId());
        }

        return driverDTO;
    }
}



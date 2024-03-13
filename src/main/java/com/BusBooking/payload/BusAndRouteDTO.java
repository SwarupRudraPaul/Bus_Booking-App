package com.BusBooking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusAndRouteDTO {

    private long busId;

    private String busNumber;

    private String busType;

    private double price;

    private int totalSeats;

    private int availableSeats;

    private Long routeId;

    private String fromLocation;
    private String toLocation;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String date;
}

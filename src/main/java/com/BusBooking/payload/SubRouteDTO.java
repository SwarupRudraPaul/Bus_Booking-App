package com.BusBooking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubRouteDTO {

    private Long subRouteId;
    private String fromLocation;
    private String toLocation;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String date;
    private Long routeId;
    private Long busId;

    // Constructors, getters, setters, and toString method
}


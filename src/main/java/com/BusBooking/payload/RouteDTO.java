package com.BusBooking.payload;

import com.BusBooking.entity.SubRoute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {

    private Long routeId;
    private String fromLocation;
    private String toLocation;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String date;
    private long busId;

    // Constructors, getters, setters, and toString method
}


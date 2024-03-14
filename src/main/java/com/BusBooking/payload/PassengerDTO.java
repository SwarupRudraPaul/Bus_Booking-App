package com.BusBooking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {

    private long passengerId;
    private String name;
    private String email;
    private String mobile;
    private long busId;
    private long routeId;
    private long subRouteId;
}

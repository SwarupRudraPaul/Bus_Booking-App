package com.BusBooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long passengerId;
    private String name;
    private String email;
    private String mobile;

    @Column(name = "bus_id", unique = true)
    private long busId;
    @Column(name = "route_id", unique = true)
    private long routeId;
    @Column(name = "subRoute_id", unique = true)
    private long subRouteId;
}


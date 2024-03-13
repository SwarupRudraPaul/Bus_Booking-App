package com.BusBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    private String fromLocation;
    private String toLocation;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String date;

    @OneToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @OneToMany(mappedBy = "route")
    private List<SubRoute> subRoute;

    // Constructors, getters, setters, and toString method
}


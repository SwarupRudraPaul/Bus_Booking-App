package com.BusBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long busId;

    private String busNumber;

    private String busType;

    private double price;

    private int totalSeats;

    private int availableSeats;

    @OneToOne(mappedBy = "bus")
    private Driver driver;

    @OneToOne(mappedBy = "bus")
    private Route route;

    @OneToMany(mappedBy = "bus")
    private List<SubRoute> subRoute;
}


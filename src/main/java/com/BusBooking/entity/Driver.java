package com.BusBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String name;

    private String email;

    private String phone;

    private String drivingLicenseNumber;

    @OneToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
}



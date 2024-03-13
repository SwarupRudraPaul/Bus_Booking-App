package com.BusBooking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

    private Long driverId;

    private String name;

    private String email;

    private String phone;

    private String drivingLicenseNumber;

    private Long busId; // Assuming you want to include busId in the DTO for reference
}


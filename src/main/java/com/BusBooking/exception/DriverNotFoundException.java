package com.BusBooking.exception;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String s) {
        super(s);
    }
}

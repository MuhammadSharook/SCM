package com.example.demo.Exception;

public class CarrierUnavailableException extends RuntimeException {
    public CarrierUnavailableException(String message) {
        super(message);
    }
}

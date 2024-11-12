package com.example.demo.Exception;

public class WarehouseUnavailable extends RuntimeException {
    public WarehouseUnavailable(String message) {
        super(message);
    }
}

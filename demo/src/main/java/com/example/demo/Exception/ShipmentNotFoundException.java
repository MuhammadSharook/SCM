package com.example.demo.Exception;

public class ShipmentNotFoundException extends RuntimeException{
    public ShipmentNotFoundException(String message){
        super(message);
    }
}

package com.example.demo.Exception;

import com.example.demo.DTO.Response.WarehouseResponse;

public class WarehouseUnavailable extends RuntimeException{
    public WarehouseUnavailable(String message){
        super(message);
    }
}

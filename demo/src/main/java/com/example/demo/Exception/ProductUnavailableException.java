package com.example.demo.Exception;

public class ProductUnavailableException extends RuntimeException{
    public ProductUnavailableException(String message){
        super(message);
    }
}

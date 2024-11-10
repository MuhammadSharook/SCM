package com.example.demo.Service;

import com.example.demo.DTO.Request.ShipmentRequest;
import com.example.demo.DTO.Response.ShipmentResponse;

public interface ShipmentService {
    ShipmentResponse createShipment(ShipmentRequest shipmentRequest);
}

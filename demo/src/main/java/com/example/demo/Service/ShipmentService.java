package com.example.demo.Service;

import com.example.demo.DTO.Request.ShipmentRequest;
import com.example.demo.DTO.Response.ShipmentResponse;

import java.util.List;

public interface ShipmentService {
    ShipmentResponse createShipment(ShipmentRequest shipmentRequest);

    List<ShipmentResponse> getAllShipment();

    ShipmentResponse getShipmentId(int shipmentId);

    ShipmentResponse updateShipment(ShipmentRequest shipmentRequest);

    String deleteShipment(int trackingId);
}

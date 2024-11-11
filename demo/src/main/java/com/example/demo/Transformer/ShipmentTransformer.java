package com.example.demo.Transformer;

import com.example.demo.DTO.Request.ShipmentRequest;
import com.example.demo.DTO.Response.ShipmentResponse;
import com.example.demo.Model.Shipment;

public class ShipmentTransformer {
    public static Shipment fromShipmentRequestTOShipment(ShipmentRequest shipmentRequest) {
        return Shipment.builder()
                .origin(shipmentRequest.getOrigin())
                .destination(shipmentRequest.getDestination())
                .volume(shipmentRequest.getVolume())
                .weight(shipmentRequest.getWeight())
                .deliveryWindowStart(shipmentRequest.getDeliveryWindowStart())
                .deliveryWindowEnd(shipmentRequest.getDeliveryWindowEnd())
                .build();
    }

    public static ShipmentResponse fromShipmentTOShipmentResponse(Shipment shipment) {
        return ShipmentResponse.builder()
                .origin(shipment.getOrigin())
                .destination(shipment.getDestination())
                .cost(shipment.getCost())
                .shipmentStatus(shipment.getShipmentStatus())
                .trackingID(shipment.getTrackingID())
                .build();
    }
}

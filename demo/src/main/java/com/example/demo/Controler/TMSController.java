package com.example.demo.Controler;

import com.example.demo.DTO.Request.ShipmentRequest;
import com.example.demo.DTO.Response.ShipmentResponse;
import com.example.demo.Service.CarrierService;
import com.example.demo.Service.RouteService;
import com.example.demo.Service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tms")
public class TMSController {

    private final ShipmentService shipmentService;
    private final CarrierService carrierService;
    private final RouteService routeService;

    public TMSController(ShipmentService shipmentService,
                         CarrierService carrierService,
                         RouteService routeService) {
        this.shipmentService = shipmentService;
        this.carrierService = carrierService;
        this.routeService = routeService;
    }

    public ResponseEntity createShipment(ShipmentRequest shipmentRequest)
    {
        try {
            ShipmentResponse response = shipmentService.createShipment(shipmentRequest);

            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}

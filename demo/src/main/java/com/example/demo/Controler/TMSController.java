package com.example.demo.Controler;

import com.example.demo.DTO.Request.ShipmentRequest;
import com.example.demo.DTO.Response.ShipmentResponse;
import com.example.demo.Model.Shipment;
import com.example.demo.Service.CarrierService;
import com.example.demo.Service.RouteService;
import com.example.demo.Service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create-shipment/")
    public ResponseEntity createShipment(@RequestBody ShipmentRequest shipmentRequest)
    {
        try {
            ShipmentResponse response = shipmentService.createShipment(shipmentRequest);

            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-all-shipments")
    public ResponseEntity getAllShipment(){
       List<ShipmentResponse> shipmentResponses = shipmentService.getAllShipment();

       return new ResponseEntity(shipmentResponses,HttpStatus.OK);
    }

    @GetMapping("/find-shipment/{shipmentID}")
    public ResponseEntity getShipmentId(@RequestParam("shipmentID") int shipmentId){
        try {
            ShipmentResponse shipment = shipmentService.getShipmentId(shipmentId);
            return new ResponseEntity(shipment,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update-shipment")
    public ResponseEntity updateShipment(@RequestBody ShipmentRequest shipmentRequest){
        ShipmentResponse shipmentResponse = shipmentService.updateShipment(shipmentRequest);

        return new ResponseEntity(shipmentResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-shipment/{trackingID}")
    public ResponseEntity deleteShipment(@PathVariable("trackingID") int trackingId){
        String response = shipmentService.deleteShipment(trackingId);

        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }
}

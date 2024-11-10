package com.example.demo.Controler;

import com.example.demo.DTO.Request.CarrierRequest;
import com.example.demo.DTO.Request.ShipmentRequest;
import com.example.demo.DTO.Response.CarrierResponse;
import com.example.demo.DTO.Response.ShipmentResponse;
import com.example.demo.Enum.CarrierStatus;
import com.example.demo.Model.Carrier;
import com.example.demo.Model.Shipment;
import com.example.demo.Service.CarrierService;
import com.example.demo.Service.RouteService;
import com.example.demo.Service.ShipmentService;
import com.example.demo.Transformer.CarrierTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
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

    @PostMapping("/create-shipment")
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

    @GetMapping("/get-all-carriers")
    public ResponseEntity getAllCarriers(){

        List<CarrierResponse> response = carrierService.getAllCarriers();

        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/find-carrier/{carrierID}")
    public ResponseEntity getCarrierById(@PathVariable("carrierID")int carrierID){
        try {
            CarrierResponse response = carrierService.getCarrierById(carrierID);
            return new ResponseEntity(response,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-carrier")
    public ResponseEntity createCarrier(@RequestBody CarrierRequest carrierRequest){

        CarrierResponse response = carrierService.createCarrier(carrierRequest);

        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @PutMapping("/update-carrier")
    public ResponseEntity updateCarrier(@RequestBody CarrierRequest carrierRequest){

        CarrierResponse response = carrierService.updateCarrier(carrierRequest);

        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-carrier/{carrierNo}")
    public ResponseEntity deleteCarrier(@PathVariable("CarrierNo")int carrierNo){
        try {
            String response = carrierService.deleteCarrier(carrierNo);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-carrier-rate")
    public ResponseEntity getCarrierRate(@RequestBody CarrierRequest carrierRequest){

        Carrier carrier = CarrierTransformer.fromCarrierRequestTOCarrier(carrierRequest);
        try {
            double rate = carrierService.getCarrierRate(CarrierTransformer.fromCarrierTOCarrierResponse(carrier));
            return new ResponseEntity(rate, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-available-carriers")
    public ResponseEntity getAvailableCarriers(){
        List<Carrier> carrierList = carrierService.getAvailableCarriers();

        List<CarrierResponse> carrierResponses = new ArrayList<>();
        for(Carrier carrier: carrierList){
            carrierResponses.add(CarrierTransformer.fromCarrierTOCarrierResponse(carrier));
        }

        return new ResponseEntity(carrierResponses,HttpStatus.FOUND);
    }

    @PutMapping("/update-carrier-status")
    public ResponseEntity updateCarrierStatus(CarrierRequest carrierRequest, CarrierStatus carrierStatus){
        Carrier carrier = CarrierTransformer.fromCarrierRequestTOCarrier(carrierRequest);

        carrierService.updateCarrierStatus(carrier,carrierStatus);

        return new ResponseEntity("Successfully updated.",HttpStatus.OK);
    }
}

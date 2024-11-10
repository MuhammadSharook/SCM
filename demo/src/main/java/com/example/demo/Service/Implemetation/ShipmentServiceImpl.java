package com.example.demo.Service.Implemetation;

import com.example.demo.DTO.Request.ShipmentRequest;
import com.example.demo.DTO.Response.ShipmentResponse;
import com.example.demo.Enum.CarrierStatus;
import com.example.demo.Exception.CarrierUnavailableException;
import com.example.demo.Model.Carrier;
import com.example.demo.Model.Route;
import com.example.demo.Model.Shipment;
import com.example.demo.Repository.ShipmentRepository;
import com.example.demo.Service.*;
import com.example.demo.Transformer.ShipmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.demo.Utils.helper.calculateAdditionalFees;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final CarrierService carrierService;
    private final RouteServiceImpl routeService;
    private final LocationService locationService;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository,
                               CarrierService carrierService,
                               RouteServiceImpl routeService,
                               LocationService locationService)
                                {
        this.shipmentRepository = shipmentRepository;
        this.carrierService = carrierService;
        this.routeService = routeService;
        this.locationService = locationService;
    }

    @Override
    public ShipmentResponse createShipment(ShipmentRequest shipmentRequest) {

        validateShipmentData(shipmentRequest);

        Shipment shipment = ShipmentTransformer.fromShipmentRequestTOShipment(shipmentRequest);

        shipment.setCost(calculateShipmentCost(shipment));

        Carrier carrier = assignCarrier(shipment);

        shipment.setCarrier(new ArrayList<>((Collection) carrier));

        // 4. Generate a Unique Tracking ID
        shipment.setTrackingID(generateTrackingId());

        Route route = routeService.calculateOptimalRoute(shipment);

        shipment.setRoute((List<Route>) route);

        Shipment savedShipment = shipmentRepository.save(shipment);

        // 9. Send Shipment Confirmation Email

        // 10. Update Carrier Availability
        carrierService.updateCarrierStatus(carrier, CarrierStatus.BUSY);

        return ShipmentTransformer.fromShipmentTOShipmentResponse(savedShipment);
    }

    private String generateTrackingId() {

        // Generate a unique tracking ID using UUID
        return UUID.randomUUID().toString();
    }

    private Carrier assignCarrier(Shipment shipment) {
        // Get available carriers based on criteria (e.g., vehicle type, service area)

        List<Carrier> availableCarriers = carrierService.getAvailableCarriers();

        // If no available carriers, throw an exception
        if (availableCarriers.isEmpty()) {
            throw new CarrierUnavailableException("No available carriers found.");
        }

        // Sort carriers by rate (or other criteria)
        availableCarriers.sort(Comparator.comparingDouble(Carrier::getRate));

        // Assign the carrier with the lowest rate (or other criteria)
        return availableCarriers.get(0);
    }

    private double calculateShipmentCost(Shipment shipment) {

        double distance = locationService.calculateDistance(shipment.getOrigin(), shipment.getDestination());

        double ratePerMile = carrierService.getCarrierRate(shipment.getCarrier().get(0));

        double baseCost = distance * ratePerMile;


        double additionalFees = calculateAdditionalFees(shipment);

        return baseCost + additionalFees;
    }


    private void validateShipmentData(ShipmentRequest shipmentRequest) {

        if (shipmentRequest.getOrigin() == null || shipmentRequest.getOrigin().isEmpty()) {

            throw new IllegalArgumentException("Origin is required.");
        }
        if (shipmentRequest.getDestination() == null || shipmentRequest.getDestination().isEmpty()) {
            throw new IllegalArgumentException("Destination is required.");
        }
        if (shipmentRequest.getDeliveryWindowStart().after(shipmentRequest.getDeliveryWindowEnd())) {
            throw new IllegalArgumentException("Delivery window start cannot be after delivery window end.");
        }
        if (shipmentRequest.getWeight() > 10) {
            throw new IllegalArgumentException("Shipment weight cannot be greater than 10Kg.");
        }
    }

}

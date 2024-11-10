package com.example.demo.Service.Implemetation;

import com.example.demo.DTO.Request.CarrierRequest;
import com.example.demo.DTO.Response.CarrierResponse;
import com.example.demo.Enum.CarrierStatus;
import com.example.demo.Exception.CarrierUnavailableException;
import com.example.demo.Model.Carrier;
import com.example.demo.Model.Shipment;
import com.example.demo.Repository.CarrierRepository;
import com.example.demo.Service.CarrierService;
import com.example.demo.Transformer.CarrierTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {

    private final CarrierRepository carrierRepository;

    @Autowired
    public CarrierServiceImpl(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    @Override
    public double getCarrierRate(CarrierResponse carrierResponse) {
        Carrier carrier = carrierRepository.findByCarrierNo(carrierResponse.getCarrierNO());

        if(carrier == null){
            throw new CarrierUnavailableException("Carrier unavailable.");
        }

        return carrier.getRate();
    }

    @Override
    public List<Carrier> getAvailableCarriers() {
        // Get all carriers with available capacity and matching criteria (e.g., vehicle type, service area)
        // ...
        return carrierRepository.findAllByCarrierStatus(CarrierStatus.AVAILABLE);
    }

    @Override
    public void updateCarrierStatus(Carrier carrier, CarrierStatus carrierStatus) {

        // Update the carrier's status in the database
        // ...
        carrier.setCarrierStatus(carrierStatus);
        carrierRepository.save(carrier);
    }

    @Override
    public List<CarrierResponse> getAllCarriers() {
        List<CarrierResponse> responses = new ArrayList<>();

        for(Carrier carrier : carrierRepository.findAll()){
            responses.add(CarrierTransformer.fromCarrierTOCarrierResponse(carrier));
        }

        return responses;
    }

    @Override
    public CarrierResponse getCarrierById(int carrierID) {
        Carrier carrier = carrierRepository.findById(carrierID).orElse( null);

        if(carrier == null){
            throw new CarrierUnavailableException("Carrier not found.");
        }

        return CarrierTransformer.fromCarrierTOCarrierResponse(carrier);
    }

    @Override
    public CarrierResponse createCarrier(CarrierRequest carrierRequest) {
        Carrier carrier = CarrierTransformer.fromCarrierRequestTOCarrier(carrierRequest);

        carrier.setCarrierStatus(carrierRequest.getCarrierStatus());
        carrier.setRate(carrierRequest.getRate());
        carrier.setCarrierNo(carrierRequest.getCarrierNo());

        Carrier savedCarrier = carrierRepository.save(carrier);

        return CarrierTransformer.fromCarrierTOCarrierResponse(savedCarrier);
    }

    @Override
    public CarrierResponse updateCarrier(CarrierRequest carrierRequest) {
        Carrier carrier = carrierRepository.findByCarrierNo(carrierRequest.getCarrierNo());

        if(carrier == null){
            throw new CarrierUnavailableException("Carrier unavailable.");
        }

        carrier.setRate(carrierRequest.getRate());
        carrier.setCarrierStatus(carrierRequest.getCarrierStatus());
        carrier.setMobileNO(carrierRequest.getMobileNo());
        carrier.setCarrierName(carrier.getCarrierName());

        Carrier savedCarrier = carrierRepository.save(carrier);

        return CarrierTransformer.fromCarrierTOCarrierResponse(savedCarrier);
    }

    @Override
    public String deleteCarrier(int carrierNo) {
        Carrier carrier = carrierRepository.findByCarrierNo(carrierNo);

        if(carrier == null){
            throw new CarrierUnavailableException("Carrier unavailable.");
        }

        carrierRepository.delete(carrier);

        return "Successfully deleted.";
    }
}

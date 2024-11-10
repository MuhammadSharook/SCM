package com.example.demo.Service.Implemetation;

import com.example.demo.Enum.CarrierStatus;
import com.example.demo.Model.Carrier;
import com.example.demo.Repository.CarrierRepository;
import com.example.demo.Service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {

    private final CarrierRepository carrierRepository;

    @Autowired
    public CarrierServiceImpl(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    @Override
    public double getCarrierRate(Carrier carrier) {
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
}

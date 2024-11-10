package com.example.demo.Service;

import com.example.demo.DTO.Request.CarrierRequest;
import com.example.demo.DTO.Response.CarrierResponse;
import com.example.demo.Enum.CarrierStatus;
import com.example.demo.Model.Carrier;

import java.util.List;

public interface CarrierService {
    double getCarrierRate(CarrierResponse carrierResponse);

    List<Carrier> getAvailableCarriers();

    void updateCarrierStatus(Carrier carrier, CarrierStatus carrierStatus);

    List<CarrierResponse> getAllCarriers();

    CarrierResponse getCarrierById(int carrierID);

    CarrierResponse createCarrier(CarrierRequest carrierRequest);

    CarrierResponse updateCarrier(CarrierRequest carrierRequest);

    String deleteCarrier(int carrierNo);
}

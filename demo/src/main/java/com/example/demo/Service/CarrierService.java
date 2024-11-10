package com.example.demo.Service;

import com.example.demo.Enum.CarrierStatus;
import com.example.demo.Model.Carrier;

import java.util.List;

public interface CarrierService {
    double getCarrierRate(Carrier carrier);

    List<Carrier> getAvailableCarriers();

    void updateCarrierStatus(Carrier carrier, CarrierStatus carrierStatus);
}

package com.example.demo.Transformer;

import com.example.demo.DTO.Request.CarrierRequest;
import com.example.demo.DTO.Response.CarrierResponse;
import com.example.demo.Model.Carrier;

public class CarrierTransformer {

    public static Carrier fromCarrierRequestTOCarrier(CarrierRequest carrierRequest){
        return Carrier.builder()
                .carrierName(carrierRequest.getCarrierName())
                .carrierStatus(carrierRequest.getCarrierStatus())
                .rate(carrierRequest.getRate())
                .mobileNO(carrierRequest.getMobileNo())
                .build();
    }

    public static CarrierResponse fromCarrierTOCarrierResponse(Carrier carrier){
        return CarrierResponse.builder()
                .carrierName(carrier.getCarrierName())
                .carrierStatus(carrier.getCarrierStatus())
                .mobileNO(carrier.getMobileNO())
                .rate(carrier.getRate())
                .rate(carrier.getRate())
                .build();
    }
}

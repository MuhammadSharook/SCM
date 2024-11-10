package com.example.demo.Utils;

import com.example.demo.Enum.UrgencyLevel;
import com.example.demo.Model.Shipment;

import java.time.ZoneOffset;
import java.util.Date;

public class helper {

    public static double calculateAdditionalFees(Shipment shipment){
        double additionalFees = 0;

        // 1. Fuel Surcharge
        if(shipment.isFuelSurchargeEnabled()){
            double fuelPriceIndex = getFuelPriceIndex();
            additionalFees += calculateFuelSurcharge(fuelPriceIndex,shipment.getDistance());
        }

        // 2. Handling Fee
        if (shipment.isHandlingFeeEnabled()) {
            // Calculate handling fee based on shipment weight or volume
            additionalFees += calculateHandlingFee(shipment.getWeight(), shipment.getVolume());
        }

        // 3. Hazardous Material Fee
        if (shipment.isHazardousMaterial()) {
            // Calculate hazardous material fee based on material type and shipment weight
            additionalFees += calculateHazardousMaterialFee(shipment.getHazardousMaterialType(), shipment.getWeight());
        }

        // 4. Oversize/Overweight Fee
        if (shipment.getWeight() > 10) {
            // Calculate oversize/overweight fee based on dimensions and weight
            additionalFees += calculateOversizeOverweightFee(shipment.getLength(), shipment.getWidth(), shipment.getHeight(), shipment.getWeight());
        }

        return additionalFees;
    }



    private static double calculateOversizeOverweightFee(double length, double width, double height, double weight) {

        // Calculate oversize/overweight fee based on dimensions and weight
        // ...
        return (length * width * height * 0.1) + (weight * 0.2); // Example
    }

    private static double calculateHazardousMaterialFee(String hazardousMAterialType, double weight) {

        // Calculate hazardous material fee based on material type and weight
        // ...
        return weight * 5; // Example
    }

    private static double calculateHandlingFee(double weight, double volume) {

        // Calculate handling fee based on weight or volume
        // ...
        return (weight * 0.1) + (volume * 0.02); // Example
    }

    private static double calculateFuelSurcharge(double fuelPriceIndex, double distance) {

        // Calculate fuel surcharge based on fuel price index and distance

        return fuelPriceIndex * distance * 0.05; // Example
    }

    private static double getFuelPriceIndex(){

        return 3.5;
    }
}

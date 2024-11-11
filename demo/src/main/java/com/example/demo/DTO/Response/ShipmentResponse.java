package com.example.demo.DTO.Response;

import com.example.demo.Enum.ShipmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentResponse {

    String origin;

    String destination;

    double cost;

    @Enumerated(value = EnumType.STRING)
    ShipmentStatus shipmentStatus;

    int trackingID;


}

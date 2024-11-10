package com.example.demo.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequest {

    String origin;

    String destination;

    double volume;

    double weight;

    Date deliveryWindowStart;

    Date deliveryWindowEnd;
}

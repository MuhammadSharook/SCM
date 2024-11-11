package com.example.demo.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {

    double totalDistance;

    double cost;

    double estimatedTime;

}

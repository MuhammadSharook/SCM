package com.example.demo.Transformer;

import com.example.demo.DTO.Response.RouteResponse;
import com.example.demo.Model.Route;

public class RouteTransformer {

    public static RouteResponse fromRouteTORouteResponse(Route route) {
        return RouteResponse.builder()
                .totalDistance(route.getTotalDistance())
                .cost(route.getCost())
                .estimatedTime(route.getEstimatedTime())
                .build();
    }
}

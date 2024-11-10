package com.example.demo.Service.Implemetation;

import com.example.demo.Model.Route;
import com.example.demo.Model.Shipment;
import com.example.demo.Service.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    public Route calculateOptimalRoute(Shipment shipment) {

        Route route = new Route();

        return route;
    }
}

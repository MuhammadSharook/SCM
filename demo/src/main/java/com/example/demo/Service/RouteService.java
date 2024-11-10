package com.example.demo.Service;

import com.example.demo.Model.Route;
import com.example.demo.Model.Shipment;

public interface RouteService {
    Route calculateOptimalRoute(Shipment shipment);
}

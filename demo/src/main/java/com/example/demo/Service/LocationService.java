package com.example.demo.Service;

import com.example.demo.Model.Location;

import java.util.List;

public interface LocationService {
    double calculateDistance(String origin, String destination);

    List<Location> getAllLocations();
}

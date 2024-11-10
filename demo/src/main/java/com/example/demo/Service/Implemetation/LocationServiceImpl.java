package com.example.demo.Service.Implemetation;

import com.example.demo.Service.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    private static final double EARTH_RADIUS_KM = 6371;
    @Override
    public double calculateDistance(String origin, String destination) {

        return 0;
    }

}

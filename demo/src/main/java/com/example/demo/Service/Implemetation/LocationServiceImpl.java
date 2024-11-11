package com.example.demo.Service.Implemetation;

import com.example.demo.Model.Location;
import com.example.demo.Repository.LocationRepository;
import com.example.demo.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private static final double EARTH_RADIUS_KM = 6371;
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public double calculateDistance(String origin, String destination) {

        return 0;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

}

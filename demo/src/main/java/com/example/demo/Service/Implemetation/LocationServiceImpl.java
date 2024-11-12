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

        double lat1 = Math.toRadians(locationRepository.findByAddress(origin).getLatitude());
        double lon1 = Math.toRadians(locationRepository.findByAddress(origin).getLongitude());
        double lat2 = Math.toRadians(locationRepository.findByAddress(destination).getLatitude());
        double lon2 = Math.toRadians(locationRepository.findByAddress(destination).getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }


    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

}

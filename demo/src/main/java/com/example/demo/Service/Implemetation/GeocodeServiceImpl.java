package com.example.demo.Service.Implemetation;

import com.example.demo.Model.GeoCode;
import com.example.demo.Model.Location;
import com.example.demo.Repository.GeocodeRepository;
import com.example.demo.Service.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeocodeServiceImpl implements GeoCodingService {

    private final GeocodeRepository geocodeRepository;

    @Autowired
    public GeocodeServiceImpl(GeocodeRepository geocodeRepository) {
        this.geocodeRepository = geocodeRepository;
    }

    @Override
    public Location geocodeAddress(String address) {
        Location location = new Location();

        GeoCode geoCode = geocodeRepository.findByAddress(address);

        if (geoCode == null) {
            throw new RuntimeException("Invalid address.");
        }

        location.setAddress(geoCode.getAddress());
        location.setLatitude(geoCode.getLatitude());
        location.setLongitude(geoCode.getLongitude());

        return location;
    }
}

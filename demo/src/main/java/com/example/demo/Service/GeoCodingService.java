package com.example.demo.Service;

import com.example.demo.Model.Location;

public interface GeoCodingService {
    Location geocodeAddress(String address);
}

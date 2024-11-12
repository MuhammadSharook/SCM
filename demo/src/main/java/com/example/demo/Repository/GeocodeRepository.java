package com.example.demo.Repository;

import com.example.demo.Model.GeoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeocodeRepository extends JpaRepository<GeoCode, Integer> {
    GeoCode findByAddress(String address);
}

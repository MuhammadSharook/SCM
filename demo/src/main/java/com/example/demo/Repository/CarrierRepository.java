package com.example.demo.Repository;

import com.example.demo.Enum.CarrierStatus;
import com.example.demo.Model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier,Integer> {
    List<Carrier> findAllByCarrierStatus(CarrierStatus carrierStatus);

    Carrier findByCarrierNo(int carrierNo);
}

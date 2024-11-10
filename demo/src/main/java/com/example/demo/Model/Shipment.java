package com.example.demo.Model;

import com.example.demo.Enum.ShipmentStatus;
import com.example.demo.Enum.UrgencyLevel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String origin;

    String destination;

    Date deliveryWindowStart;

    Date deliveryWindowEnd;

    double weight;

    double volume;

    double cost;

    @Enumerated(value = EnumType.STRING)
    ShipmentStatus shipmentStatus;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    List<Carrier> carrier;

    String hazardousMaterialType;

    boolean fuelSurchargeEnabled;

    boolean handlingFeeEnabled;

    boolean hazardousMaterial;

    boolean overSize;

    boolean timeSensitive;

    double distance;

    double length;

    double width;

    double height;

    int trackingID;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    List<Route> route;

    @Enumerated(value = EnumType.STRING)
    UrgencyLevel urgencyLevel;
}

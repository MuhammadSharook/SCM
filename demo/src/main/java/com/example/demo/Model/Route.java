package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    double totalDistance;

    double cost;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    List<Location> locationList;

    @ManyToOne
    @JoinColumn
    Shipment shipment;

    double estimatedTime;
}

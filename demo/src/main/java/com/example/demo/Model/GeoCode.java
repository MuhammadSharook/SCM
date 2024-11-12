package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "geocode")
public class GeoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String address;

    double latitude;

    double longitude;

}

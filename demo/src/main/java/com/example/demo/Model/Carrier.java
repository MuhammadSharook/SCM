package com.example.demo.Model;

import com.example.demo.Enum.CarrierStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carrier")
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String carrierName;

    String mobileNO;

    double rate;

    @Enumerated(value = EnumType.STRING)
    CarrierStatus carrierStatus;

    @ManyToOne
    @JoinColumn
    Shipment shipment;
}

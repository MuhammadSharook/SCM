package com.example.demo.DTO.Request;

import com.example.demo.Enum.CarrierStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrierRequest {

    String carrierName;

    String mobileNo;

    int CarrierNo;

    double rate;

    @Enumerated(value = EnumType.STRING)
    CarrierStatus carrierStatus;
}

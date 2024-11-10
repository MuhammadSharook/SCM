package com.example.demo.DTO.Response;

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
public class CarrierResponse {

    String carrierName;

    String mobileNO;

    double rate;

    int carrierNO;

    @Enumerated(value = EnumType.STRING)
    CarrierStatus carrierStatus;
}

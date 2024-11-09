package com.example.demo.DTO.Response;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemResponse {

    int requiredQuantity;

    double price;

    String productName;

    String discription;
}

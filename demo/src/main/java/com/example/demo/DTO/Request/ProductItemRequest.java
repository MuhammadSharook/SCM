package com.example.demo.DTO.Request;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemRequest {

    int requiredQuantity;

    String customerMobileNO;

    int listItemID;
}
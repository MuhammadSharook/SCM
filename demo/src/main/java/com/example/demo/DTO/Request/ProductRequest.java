package com.example.demo.DTO.Request;

import com.example.demo.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    int requiredQuantity;

    double price;

    String productName;

    String discription;

    Category category;

    int reorderPoint;

}

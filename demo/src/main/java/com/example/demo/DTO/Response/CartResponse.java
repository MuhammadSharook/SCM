package com.example.demo.DTO.Response;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    double cartTotal;

    List<ProductItemResponse> productItems;
}

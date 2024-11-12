package com.example.demo.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponse {

    int warehouseNo;

    String warehouseName;

    List<ProductItemResponse> productItemResponseList;

}

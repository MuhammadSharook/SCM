package com.example.demo.DTO.Response;

import com.example.demo.Enum.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntityResponse {

    String orderId;

    double orderTotal;

    Data orderTime;

    OrderStatus orderStatus;

    String customerName;

    String customerMobileNO;

    List<ProductItemResponse> productItemResponseList;
}

package com.example.demo.Transformer;

import com.example.demo.DTO.Response.OrderEntityResponse;
import com.example.demo.DTO.Response.ProductItemResponse;
import com.example.demo.Model.Cart;
import com.example.demo.Model.OrderEntity;
import com.example.demo.Model.ProductItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderEntityTransformer {

    public static OrderEntity prepareOrderEntity(Cart cart) {
        return OrderEntity.builder()
                .orderID(String.valueOf(UUID.randomUUID()))
                .orderTotal(cart.getCartTotal())
                .build();
    }

    public static OrderEntityResponse fromOrderEntityTOOrderEntityResponse(OrderEntity savedOrder) {

        List<ProductItemResponse> productItemResponseList = new ArrayList<>();
        for (ProductItem productItem : savedOrder.getProductItems()) {
            ProductItemResponse productItemResponse = ProductItemResponse.builder()
                    .productName(productItem.getProductName())
                    .price(productItem.getPrice())
                    .requiredQuantity(productItem.getQuantity())
                    .discription(productItem.getDiscription())
                    .build();
        }

        return OrderEntityResponse.builder()
                .orderId(savedOrder.getOrderID())
                .orderTotal(savedOrder.getOrderTotal())
                .orderTime((Data) savedOrder.getOrderTime())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobileNO(savedOrder.getCustomer().getMobileNO())
                .productItemResponseList(productItemResponseList)
                .build();
    }
}

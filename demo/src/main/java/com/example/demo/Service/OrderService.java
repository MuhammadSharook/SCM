package com.example.demo.Service;

import com.example.demo.DTO.Response.OrderEntityResponse;
import com.example.demo.Enum.OrderStatus;
import com.example.demo.Model.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntityResponse createOrder(String mobileNO);

    List<OrderEntity> getOrderHistory(String mobileNO);

    OrderEntityResponse updateOrderStatus(int orderID, OrderStatus status);
}

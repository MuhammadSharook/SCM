package com.example.demo.Controler;

import com.example.demo.DTO.Response.OrderEntityResponse;
import com.example.demo.Enum.OrderStatus;
import com.example.demo.Model.OrderEntity;
import com.example.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oms")
public class OMSController {


    private final OrderService orderService;

    @Autowired
    public OMSController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-order/{mobile}")
    public ResponseEntity createOrder(@PathVariable("mobile") String mobileNO) {

        try {
            OrderEntityResponse orderEntityResponse = orderService.createOrder(mobileNO);
            return new ResponseEntity(orderEntityResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history/{mobileNO}")
    public ResponseEntity getOrderHistory(@PathVariable("mobileNO") String mobileNO) {

        try {
            List<OrderEntity> orders = orderService.getOrderHistory(mobileNO);
            return new ResponseEntity(orders, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/change-order-status/{orderID}")
    public ResponseEntity updateOrderStatus(@PathVariable("oderId") int orderID, @RequestBody OrderStatus status) {

        try {
            OrderEntityResponse orderEntityResponse = orderService.updateOrderStatus(orderID, status);
            return new ResponseEntity(orderEntityResponse, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

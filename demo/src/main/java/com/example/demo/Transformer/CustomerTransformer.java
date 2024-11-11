package com.example.demo.Transformer;

import com.example.demo.DTO.Request.CustomerRequest;
import com.example.demo.DTO.Response.CartResponse;
import com.example.demo.DTO.Response.CustomerResponse;
import com.example.demo.Model.Customer;

public class CustomerTransformer {

    public static Customer fromCustomerRequestTOCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .email(customerRequest.getEmail())
                .role(customerRequest.getRole())
                .gender(customerRequest.getGender())
                .mobileNO(customerRequest.getMobileNO())
                .build();
    }

    public static CustomerResponse fromCustomerTOCustomerResponse(Customer customer) {
        CartResponse cartResponse = CartTransformer.fromCartTOCartResponse(customer.getCart());

        return CustomerResponse.builder()
                .name(customer.getName())
                .mobileNO(customer.getMobileNO())
                .address(customer.getAddress())
                .role(customer.getRole())
                .email(customer.getEmail())
                .cart(cartResponse)
                .gender(customer.getGender())
                .build();
    }
}

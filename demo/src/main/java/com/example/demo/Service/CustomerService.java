package com.example.demo.Service;

import com.example.demo.DTO.Request.CustomerRequest;
import com.example.demo.DTO.Response.CustomerResponse;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerByMobileNO(String mobileNO);

    String deleteCustomer(String mobileNO);

    Object getAllCustomer();
}

package com.example.demo.Controler;

import com.example.demo.DTO.Request.CustomerRequest;
import com.example.demo.DTO.Response.CustomerResponse;
import com.example.demo.Model.Customer;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);

        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/mobile/{mobile}")
    public ResponseEntity getCustomerByMobileNo(@PathVariable("mobile") String mobileNO){
        try
        {
            CustomerResponse customerResponse = customerService.getCustomerByMobileNO(mobileNO);
            return new ResponseEntity(customerResponse,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{mobileNo}")
    public ResponseEntity deleteCustomer(@PathVariable("mobile")String mobileNO){
        try {
            String response = customerService.deleteCustomer(mobileNO);
            return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-customers")
    public ResponseEntity getAllCustomer(){
        List<CustomerResponse> customerList = (List<CustomerResponse>) customerService.getAllCustomer();

        return new ResponseEntity(customerList,HttpStatus.OK);
    }
}

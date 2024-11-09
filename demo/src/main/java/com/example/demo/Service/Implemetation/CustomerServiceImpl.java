package com.example.demo.Service.Implemetation;

import com.example.demo.DTO.Request.CustomerRequest;
import com.example.demo.DTO.Response.CustomerResponse;
import com.example.demo.Exception.CustomerNotFoundException;
import com.example.demo.Model.Cart;
import com.example.demo.Model.Customer;
import com.example.demo.Repository.CartRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Service.CustomerService;
import com.example.demo.Transformer.CustomerTransformer;
import com.example.demo.Utils.MailComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        Customer customer = CustomerTransformer.fromCustomerRequestTOCustomer(customerRequest);

        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);
        customer.setGender(customerRequest.getGender());
        customer.setRole(customerRequest.getRole());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        Customer savedCustomer = customerRepository.save(customer);

        SimpleMailMessage message = MailComposer.composeCustomerRegistrationMail(savedCustomer);
        javaMailSender.send(message);

        return CustomerTransformer.fromCustomerTOCustomerResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getCustomerByMobileNO(String mobileNO) {
        Customer customer = customerRepository.findByMobileNO(mobileNO);

        if (customer == null) {
            throw new CustomerNotFoundException("Invalid MobileNo !!!");
        }

        return CustomerTransformer.fromCustomerTOCustomerResponse(customer);
    }


    @Override
    public String deleteCustomer(String mobileNO) {
        Customer customer = customerRepository.findByMobileNO(mobileNO);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found!!!!");
        }

        Cart cart = customer.getCart();
        cartRepository.delete(cart);

        return "Successfully deleted!!!";
    }

    @Override
    public Object getAllCustomer() {
        return customerRepository.findAll();
    }
}

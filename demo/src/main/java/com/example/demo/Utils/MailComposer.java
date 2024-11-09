package com.example.demo.Utils;

import com.example.demo.Model.Customer;
import org.springframework.mail.SimpleMailMessage;

public class MailComposer {

    public static SimpleMailMessage composeCustomerRegistrationMail(Customer customer){
        String text = "Dear " + customer.getName()+",\n"
                +"\n"
                +"You have been successfully added to India's most enthusiastic community of online delivery store. Following are the your customer details: \n"
                +"\n"
                +"Your Good Name: "+customer.getName()+"\n"
                +"Email: "+customer.getEmail()+"\n"
                +"Password: "+customer.getPassword()+"\n"
                +"Address: "+customer.getAddress()+"\n"
                +"\n"
                +"We strongly recommend you to keep your password safe. Otherwise someone else will get from your cart on your pocket :):";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Online Delivery Application Heartily Welcomes You!");
        message.setText(text);


        return message;
    }
}

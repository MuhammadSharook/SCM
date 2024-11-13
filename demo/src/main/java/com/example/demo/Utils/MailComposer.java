package com.example.demo.Utils;

import com.example.demo.Model.Customer;
import com.example.demo.Model.OrderEntity;
import com.example.demo.Model.Shipment;
import org.springframework.mail.SimpleMailMessage;

public class MailComposer {

    public static SimpleMailMessage composeCustomerRegistrationMail(Customer customer) {
        String text = "Dear " + customer.getName() + ",\n"
                + "\n"
                + "You have been successfully added to India's most enthusiastic community of online delivery store. Following are the your customer details: \n"
                + "\n"
                + "Your Good Name: " + customer.getName() + "\n"
                + "Email: " + customer.getEmail() + "\n"
                + "Password: " + customer.getPassword() + "\n"
                + "Address: " + customer.getAddress() + "\n"
                + "\n"
                + "We strongly recommend you to keep your password safe. Otherwise someone else will get from your cart on your pocket :):";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Online Delivery Application Heartily Welcomes You!");
        message.setText(text);


        return message;
    }

    public static SimpleMailMessage sendOrderConfirmationMail(OrderEntity order){
        String text = "Hi " + order.getCustomer().getName() + ",\n\n" +
                "This email confirms your recent order with SCM.\n\n" +
                "Order NUmber : " + order.getOrderID() + "\n\n" +
                "You can view your order details and track its progress here : www.SCM.track-order.com\n\n" +
                "We'll keep you updated on the status of your order.\n\n" +
                "Thanks,\n" +
                "SCM ";

        SimpleMailMessage  message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(order.getCustomer().getEmail());
        message.setSubject("Your Order is Confirmed!");
        message.setText(text);

        return message;
    }

    public static SimpleMailMessage sendOrderStatusUpdateEmail(OrderEntity order){
        String text = "Hi " + order.getCustomer().getName() + ",\n\n" +
                "This is an update on your order " + order.getOrderID() + ",\n\n" +
                "Current Status : " + order.getOrderStatus() + "\n\n" +
                "Thanks,\n" +
                "SCM ";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(order.getCustomer().getEmail());
        message.setSubject("Order Status Update - " + order.getOrderID());
        message.setText(text);

        return message;
    }

    public static SimpleMailMessage sendShipmentUpdateEmail(Shipment shipment){
        String text = "Hi " + shipment.getOrder().getCustomer().getName() + "\n\n" +
                "Your order " + shipment.getOrder().getOrderID() + " has been " + shipment.getShipmentStatus() + "\n\n" +
                "Tracking Number : " + shipment.getTrackingID() + "\n\n" +
                "You can track your package here : www.SCM.track-package.com \n\n" +
                "Estimated Delivery Date : " + shipment.getDeliveryWindowEnd() + "\n\n" +
                "Thanks,\n" +
                "SCM ";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(shipment.getOrder().getCustomer().getEmail());
        message.setSubject("Your Order is on its Way!");
        message.setText(text);

        return message;
    }

    public static SimpleMailMessage sendOrderCancellationEmail(OrderEntity order){
        String text = "Hi " + order.getCustomer().getName() + "\n\n" +
                "We're writing to confirm the cancellation of your order " + order.getOrderID() + "\n\n" +
                "We apologize for any inconvenience this may cause.\n\n" +
                "If you have any questions, please don't hesitate to contact us. \n\n" +
                "Thanks,\n" +
                "SCM ";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(order.getCustomer().getEmail());
        message.setSubject("Order Cancellation - " + order.getOrderID());
        message.setText(text);

        return message;
    }


}

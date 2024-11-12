package com.example.demo.Service.Implemetation;

import com.example.demo.DTO.Response.OrderEntityResponse;
import com.example.demo.Enum.OrderStatus;
import com.example.demo.Exception.CustomerNotFoundException;
import com.example.demo.Exception.EmptyCartException;
import com.example.demo.Exception.InsufficientStockException;
import com.example.demo.Exception.OrderNotFoundException;
import com.example.demo.Model.*;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.InventoryRepository;
import com.example.demo.Repository.OrderEntityRepository;
import com.example.demo.Repository.WarehouseRepository;
import com.example.demo.Service.OrderService;
import com.example.demo.Transformer.OrderEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderEntityRepository orderEntityRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public OrderServiceImpl(CustomerRepository customerRepository,
                            OrderEntityRepository orderEntityRepository,
                            WarehouseRepository warehouseRepository,
                            InventoryRepository inventoryRepository) {
        this.customerRepository = customerRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.warehouseRepository = warehouseRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public OrderEntityResponse createOrder(String mobileNO) {
        Customer customer = customerRepository.findByMobileNO(mobileNO);

        if (customer == null) {
            throw new CustomerNotFoundException("Invalid Mobile Number!!!!");
        }

        Cart cart = customer.getCart();
        if (cart.getProductItems().size() == 0) {
            throw new EmptyCartException("Sorry! Your cart is empty.");
        }

        for (ProductItem productItem : cart.getProductItems()) {
            Warehouse warehouse = warehouseRepository.findByWarehouseNo(productItem.getWarehouse().getWarehouseNo());
            for (ProductItem p : warehouse.getInventory()) {
                if (productItem.getQuantity() >= p.getQuantity()) {
                    throw new InsufficientStockException("Insufficient ProductItems.");
                } else {
                    p.setQuantity(p.getQuantity() - productItem.getQuantity());
                }
            }
        }

        OrderEntity order = OrderEntityTransformer.prepareOrderEntity(cart);

        OrderEntity savedOrder = orderEntityRepository.save(order);

        order.setCustomer(customer);
        order.setProductItems(cart.getProductItems());
        order.setOrderStatus(OrderStatus.ORDER_PLACED);

        customer.getOrders().add(savedOrder);

        for (ProductItem productItem : cart.getProductItems()) {
            productItem.setCart(null);
            productItem.setOrder(savedOrder);
        }

        clearCart(cart);

        customerRepository.save(customer);

        return OrderEntityTransformer.fromOrderEntityTOOrderEntityResponse(savedOrder);
    }

    @Override
    public List<OrderEntity> getOrderHistory(String mobileNO) {
        Customer customer = customerRepository.findByMobileNO(mobileNO);

        if (customer == null) {
            throw new CustomerNotFoundException("Invalid Customer ID!!");
        }

        List<OrderEntity> orderList = orderEntityRepository.findByCustomer(customer);
        if (orderList.isEmpty()) {
            throw new OrderNotFoundException("You have Zero orders!");
        }

        return orderList;
    }

    @Override
    public OrderEntityResponse updateOrderStatus(int orderID, OrderStatus status) {
        OrderEntity order = orderEntityRepository.findById(orderID).get();
        if (order == null) {
            throw new OrderNotFoundException("Invalid order ID!");
        }

        order.setOrderStatus(status);

        OrderEntity savedOrder = orderEntityRepository.save(order);

        return OrderEntityTransformer.fromOrderEntityTOOrderEntityResponse(savedOrder);
    }

    private void clearCart(Cart cart) {

        cart.setCartTotal(0);
        cart.setProductItems(new ArrayList<>());
    }
}

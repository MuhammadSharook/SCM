package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {


    List<OrderEntity> findByCustomer(Customer customer);
}

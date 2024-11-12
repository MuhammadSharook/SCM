package com.example.demo.Repository;

import com.example.demo.Model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<ProductItem,Integer> {
}

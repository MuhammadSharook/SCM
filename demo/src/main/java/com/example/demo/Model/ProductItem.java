package com.example.demo.Model;

import com.example.demo.Enum.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_item")
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int quantity;

    double price;

    String productName;

    String discription;

    int reorderPoint;

    @Enumerated(value = EnumType.STRING)
    Category category;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @ManyToOne
    @JoinColumn
    OrderEntity order;

    @ManyToOne
    @JoinColumn
    Warehouse warehouse;
}

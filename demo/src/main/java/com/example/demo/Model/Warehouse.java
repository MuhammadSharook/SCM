package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2, max = 16)
    int warehouseNo;

    String warehouseName;

    HashMap<String, ProductItem> inventory;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    List<OrderEntity> orderHistory;

    @OneToMany(mappedBy = "warehouse",cascade = CascadeType.ALL)
    List<Return> returnHistory;

}

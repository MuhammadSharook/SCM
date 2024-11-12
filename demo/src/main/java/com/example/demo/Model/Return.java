package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "return")
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn
    Warehouse warehouse;

    @OneToMany(mappedBy = "re_turn",cascade = CascadeType.ALL)
    List<ReturnItems> returnItems;
}

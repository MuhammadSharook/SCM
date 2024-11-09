package com.example.demo.DTO.Request;

import com.example.demo.Enum.Gender;
import com.example.demo.Enum.Role;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    String name;

    String email;

    String mobileNO;

    String address;

    Role role;

    Gender gender;

    String password;
}

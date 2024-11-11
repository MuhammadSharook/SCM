package com.example.demo.DTO.Response;

import com.example.demo.Enum.Gender;
import com.example.demo.Enum.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    String name;

    String email;

    String address;

    String mobileNO;

    Role role;

    Gender gender;

    CartResponse cart;
}

package com.example.demo.demo1.dto;


import com.example.demo.demo1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cc;
    private List<SimpleAddressDto> addresses;
    private List<Product> boughtProducts;



}


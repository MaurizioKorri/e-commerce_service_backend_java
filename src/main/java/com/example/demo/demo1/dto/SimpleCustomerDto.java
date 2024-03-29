package com.example.demo.demo1.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleCustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cc;

}


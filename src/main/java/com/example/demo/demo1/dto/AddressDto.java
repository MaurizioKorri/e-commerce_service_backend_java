package com.example.demo.demo1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private String city;
    private String streetAddress;
    private String zipcode;
    private String country;
    private String addressType;

    private SimpleCustomerDto customer;

}

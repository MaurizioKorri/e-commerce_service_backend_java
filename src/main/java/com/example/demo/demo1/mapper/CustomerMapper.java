package com.example.demo.demo1.mapper;

import com.example.demo.demo1.dto.CustomerDto;
import com.example.demo.demo1.dto.SimpleCustomerDto;
import com.example.demo.demo1.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    public SimpleCustomerDto toDto(Customer source);
    public Customer toEntity(CustomerDto destination);

}

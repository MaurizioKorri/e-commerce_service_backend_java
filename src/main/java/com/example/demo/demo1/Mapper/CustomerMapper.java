package com.example.demo.demo1.Mapper;

import com.example.demo.demo1.dto.CustomerDto;
import com.example.demo.demo1.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto toDto(Customer source);
    Customer toEntity(CustomerDto destination);

}

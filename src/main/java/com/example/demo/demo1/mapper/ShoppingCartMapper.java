package com.example.demo.demo1.mapper;

import com.example.demo.demo1.dto.CustomerDto;
import com.example.demo.demo1.dto.ShoppingCartDto;
import com.example.demo.demo1.dto.SimpleCustomerDto;
import com.example.demo.demo1.entity.Customer;
import com.example.demo.demo1.entity.ShoppingCart;
import org.mapstruct.Mapper;

@Mapper
public interface ShoppingCartMapper {

    public ShoppingCartDto toDto(ShoppingCart source);
    public ShoppingCart toEntity(ShoppingCartDto destination);
}

package com.example.demo.demo1.mapper;

import com.example.demo.demo1.dto.CustomersProductsDto;
import com.example.demo.demo1.dto.ShoppingCartDto;
import com.example.demo.demo1.entity.CustomersProducts;
import com.example.demo.demo1.entity.ShoppingCart;
import org.mapstruct.Mapper;


@Mapper
public interface CustomersProductsMapper {

    public CustomersProductsDto toDto(CustomersProducts source);
    public CustomersProducts toEntity(CustomersProductsDto destination);


}

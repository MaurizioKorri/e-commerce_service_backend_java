package com.example.demo.demo1.mapper;

import com.example.demo.demo1.dto.CustomersProductsItemDto;
import com.example.demo.demo1.entity.CustomersProductsItem;
import org.mapstruct.Mapper;


@Mapper
public interface CustomersProductsMapper {

    public CustomersProductsItemDto toDto(CustomersProductsItem source);
    public CustomersProductsItem toEntity(CustomersProductsItemDto destination);


}

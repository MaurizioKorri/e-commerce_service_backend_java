package com.example.demo.demo1.mapper;

import com.example.demo.demo1.dto.ShoppingCartProductDto;
import com.example.demo.demo1.entity.ShoppingCartProduct;
import org.mapstruct.Mapper;

@Mapper
public interface ShoppingCartMapper {

    public ShoppingCartProductDto toDto(ShoppingCartProduct source);
    public ShoppingCartProduct toEntity(ShoppingCartProductDto destination);
}

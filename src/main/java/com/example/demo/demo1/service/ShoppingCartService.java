package com.example.demo.demo1.service;


import com.example.demo.demo1.dto.ShoppingCartProductDto;
import com.example.demo.demo1.entity.ShoppingCartProduct;
import com.example.demo.demo1.mapper.ShoppingCartMapper;
import com.example.demo.demo1.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ShoppingCartService")
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;


    public List<ShoppingCartProductDto> getCustomerShoppingCart(Long customer_id) {
        List<ShoppingCartProduct> shoppingCartProductList = shoppingCartRepository.findCustomerShoppingCart(customer_id);
        List<ShoppingCartProductDto> sclDto = new ArrayList<>();
        for (ShoppingCartProduct sc : shoppingCartProductList) {
            sclDto.add(shoppingCartMapper.toDto(sc));
        }

        return sclDto;
    }


    public void deleteShoppingCart(Long customer_id){
        shoppingCartRepository.removeShoppingCart(customer_id);

    }





}

package com.example.demo.demo1.service;


import com.example.demo.demo1.dto.ShoppingCartDto;
import com.example.demo.demo1.entity.ShoppingCart;
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


    public List<ShoppingCartDto> getCustomerShoppingCart(Long customer_id) {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findCustomerShoppingCart(customer_id);
        List<ShoppingCartDto> sclDto = new ArrayList<>();
        for (ShoppingCart sc : shoppingCartList) {
            sclDto.add(shoppingCartMapper.toDto(sc));
        }

        return sclDto;
    }


    public void deleteShoppingCart(Long customer_id){
        shoppingCartRepository.removeShoppingCart(customer_id);

    }





}

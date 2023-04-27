package com.example.demo.demo1.controller.api;


import com.example.demo.demo1.dto.CustomersProductsItemDto;
import com.example.demo.demo1.dto.ShoppingCartProductDto;
import com.example.demo.demo1.entity.CustomersProductsItem;
import com.example.demo.demo1.mapper.CustomersProductsMapper;
import com.example.demo.demo1.repository.CustomersProductsRepository;
import com.example.demo.demo1.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customersproducts")
@CrossOrigin(origins = "*")
public class CustomersProductsController {

    @Autowired
    CustomersProductsRepository customersProductsRepository;


    @Autowired
    CustomersProductsMapper customersProductsMapper;

    @Autowired
    ShoppingCartService shoppingCartService;


    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<CustomersProductsItemDto>> getAllCustomersProducts() {
        List<CustomersProductsItem> customersProductsItemList = (List<CustomersProductsItem>) customersProductsRepository.findAll();

        List<CustomersProductsItemDto> customersProductsItemDto = new ArrayList<>();

        for (CustomersProductsItem products : customersProductsItemList) {
            customersProductsItemDto.add(customersProductsMapper.toDto(products));
        }

        return new ResponseEntity<>(customersProductsItemDto, HttpStatus.OK);
    }


    @PostMapping(value = {"/customer"})
    public ResponseEntity<List<CustomersProductsItemDto>> getCustomerProducts(@RequestBody Long customer_id) {
        List<CustomersProductsItem> customersProductsItemList = customersProductsRepository.findCustomerProducts(customer_id);

        List<CustomersProductsItemDto> customersProductsItemDto = new ArrayList<>();

        for (CustomersProductsItem products : customersProductsItemList) {
            customersProductsItemDto.add(customersProductsMapper.toDto(products));
        }

        return new ResponseEntity<>(customersProductsItemDto, HttpStatus.OK);
    }


    @PostMapping(value = {"/add"})
    public void addCustomerProducts(@RequestBody Long customer_id){

        List<ShoppingCartProductDto> shoppingCartList = shoppingCartService.getCustomerShoppingCart(customer_id);



        //add shoppingCart to CustomersProducts

        for(ShoppingCartProductDto s : shoppingCartList){
            customersProductsRepository.addShoppingCartToCustomersProducts(customer_id, s.getProduct().getId(), s.getQuantity());
        }

        //delete from shopping cart

        shoppingCartService.deleteShoppingCart(customer_id);





    }

}

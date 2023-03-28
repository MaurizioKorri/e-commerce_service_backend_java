package com.example.demo.demo1.controller.api;


import com.example.demo.demo1.dto.CustomersProductsDto;
import com.example.demo.demo1.dto.ShoppingCartDto;
import com.example.demo.demo1.entity.CustomersProducts;
import com.example.demo.demo1.entity.ShoppingCart;
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
    public ResponseEntity<List<CustomersProductsDto>> getAllCustomersProducts() {
        List<CustomersProducts> customersProductsList = (List<CustomersProducts>) customersProductsRepository.findAll();

        List<CustomersProductsDto> customersProductsDto = new ArrayList<>();

        for (CustomersProducts products : customersProductsList) {
            customersProductsDto.add(customersProductsMapper.toDto(products));
        }

        return new ResponseEntity<>(customersProductsDto, HttpStatus.OK);
    }


    @PostMapping(value = {"/customer"})
    public ResponseEntity<List<CustomersProductsDto>> getCustomerProducts(@RequestBody Long customer_id) {
        List<CustomersProducts> customersProductsList = customersProductsRepository.findCustomerProducts(customer_id);

        List<CustomersProductsDto> customersProductsDto = new ArrayList<>();

        for (CustomersProducts products : customersProductsList) {
            customersProductsDto.add(customersProductsMapper.toDto(products));
        }

        return new ResponseEntity<>(customersProductsDto, HttpStatus.OK);
    }


    @PostMapping(value = {"/add"})
    public void addCustomerProducts(@RequestBody Long customer_id){

        List<ShoppingCartDto> shoppingCartList = shoppingCartService.getCustomerShoppingCart(customer_id);



        //add shoppingCart to CustomersProducts

        for(ShoppingCartDto s : shoppingCartList){
            customersProductsRepository.addShoppingCartToCustomersProducts(customer_id, s.getProduct().getId(), s.getQuantity());
        }

        //delete from shopping cart

        shoppingCartService.deleteShoppingCart(customer_id);





    }

}

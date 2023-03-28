package com.example.demo.demo1.controller.api;
import com.example.demo.demo1.dto.ShoppingCartDto;
import com.example.demo.demo1.entity.ShoppingCart;
import com.example.demo.demo1.mapper.ShoppingCartMapper;
import com.example.demo.demo1.repository.ShoppingCartRepository;
import com.example.demo.demo1.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoppingcart")
@CrossOrigin(origins = "*")
public class ShoppingCartController {


    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;


    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<ShoppingCart>> showWholeShoppingCart(){
        List<ShoppingCart> shoppingCart = (List<ShoppingCart>) shoppingCartRepository.findAll();
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }


    @PostMapping(value = "/customer")
    public ResponseEntity<List<ShoppingCartDto>> getCustomerShoppingCart(@RequestBody Long customer_id){

        return new ResponseEntity<>(shoppingCartService.getCustomerShoppingCart(customer_id), HttpStatus.OK);

    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = {"/", ""})
    public void addToShoppingCart(@RequestBody ShoppingCart shoppingCart){

        List<ShoppingCartDto> dbShoppingCart = getCustomerShoppingCart(shoppingCart.getCustomer().getId()).getBody();

        boolean alreadyExists = false;

        for(ShoppingCartDto s : dbShoppingCart){
            if(s.getProduct().getId().equals(shoppingCart.getProduct().getId())){
                int newQuantity = shoppingCart.getQuantity() + s.getQuantity();
                alreadyExists = true;
                shoppingCartRepository.updateQuantity(newQuantity, s.getProduct().getId());
            }
        }

        if(!alreadyExists){
            shoppingCartRepository.saveInShoppingCart(shoppingCart.getProduct().getId(), shoppingCart.getCustomer().getId(), shoppingCart.getQuantity());
        }


    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = {"/delete"})
    public void deleteFromShoppingCart(@RequestBody Long customer_id){
        shoppingCartService.deleteShoppingCart(customer_id);
    }
}

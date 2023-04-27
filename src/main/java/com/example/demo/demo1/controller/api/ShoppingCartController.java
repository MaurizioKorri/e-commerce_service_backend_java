package com.example.demo.demo1.controller.api;
import com.example.demo.demo1.dto.ShoppingCartProductDto;
import com.example.demo.demo1.entity.ShoppingCartProduct;
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
    ShoppingCartService shoppingCartService;

    @PostMapping(value = "/customer")
    public ResponseEntity<List<ShoppingCartProductDto>> getCustomerShoppingCart(@RequestBody Long customer_id){
        return new ResponseEntity<>(shoppingCartService.getCustomerShoppingCart(customer_id), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = {"/", ""})
    public void addToShoppingCart(@RequestBody ShoppingCartProduct shoppingCartProduct){

        List<ShoppingCartProductDto> dbShoppingCart = getCustomerShoppingCart(shoppingCartProduct.getCustomer().getId()).getBody();
        boolean alreadyExists = false;
        for(ShoppingCartProductDto s : dbShoppingCart){
            if(s.getProduct().getId().equals(shoppingCartProduct.getProduct().getId())){
                int newQuantity = shoppingCartProduct.getQuantity() + s.getQuantity();
                alreadyExists = true;
                shoppingCartRepository.updateQuantity(newQuantity, s.getProduct().getId());
            }
        }

        if(!alreadyExists){
            shoppingCartRepository.saveInShoppingCart(shoppingCartProduct.getProduct().getId(), shoppingCartProduct.getCustomer().getId(), shoppingCartProduct.getQuantity());
        }
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = {"/delete"})
    public void deleteFromShoppingCart(@RequestBody Long customer_id){
        shoppingCartService.deleteShoppingCart(customer_id);
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/remove")
    public void removeCustomerShoppingCartItem(@RequestBody Long itemId){
        shoppingCartRepository.deleteById(itemId);
    }
}

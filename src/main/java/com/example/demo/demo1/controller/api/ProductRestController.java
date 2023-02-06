package com.example.demo.demo1.controller.api;


import com.example.demo.demo1.entity.Address;
import com.example.demo.demo1.entity.Customer;
import com.example.demo.demo1.entity.Product;
import com.example.demo.demo1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products/")
@CrossOrigin(origins = "*")

public class ProductRestController {


    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Product>> showProducts(){
        List<Product> products = (List<Product>) productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Product> show(@PathVariable("id") long id, Model model){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(value = {"/", ""})
    public Product create(@RequestBody Product newProduct){//prende l'oggetto che gli arriva dalla requestBody e con save
        // lo scrive sul db, e oltre a ciò lo ritorna (tipo del method Customer)
        //quindi possiamo controllare che sia andato tutto bene etc.
        return productRepository.save(newProduct);
        //su postman, il requestbody posso andare a metterglielo in Body (raw, JSON)
    }

    @PutMapping(value = {"/{id}"})
    public Product update(@PathVariable("id") long id, @RequestBody Product newProduct){

        return productRepository.findById(id).map(
                product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setProductDescr(newProduct.getProductDescr());
                    product.setProductWeight(newProduct.getProductWeight());
                    product.setProductPrice(newProduct.getProductPrice());
                    product.setBuyers(newProduct.getBuyers());
                    return productRepository.save(product);
                }
        ).orElseGet(() -> {
            newProduct.setId(id);
            return productRepository.save(newProduct);
        });


    }

    @DeleteMapping(value = {"/{id}"})
    public void delete(@PathVariable("id") long id) {
        productRepository.deleteById(id);
    }

}

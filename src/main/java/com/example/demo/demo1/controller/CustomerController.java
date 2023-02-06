/*
package com.example.demo.demo1.controller;

import com.example.demo.demo1.entity.Customer;
import com.example.demo.demo1.repository.CustomerRepository;
import com.example.demo.demo1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/customers/")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @GetMapping(value = {"/", "", "/index"})
    public String index(Model model){
        List<Customer> results = (List<Customer>) customerRepository.findAll();
        model.addAttribute("customers", results);
        return "customers/index";
    }

*/
/*    @GetMapping(value = {"/{id}"})
    public String show(@PathVariable("id") long id, Model model){
        Optional<Customer> customer = customerRepository.findById(id);
        model.addAttribute("customer", customer.get());
        model.addAttribute("addresses", customer.get().getAddresses());
        model.addAttribute("products", customer.get().getBoughtProducts());

        return "customers/show";
    }*//*


    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Customer> show(@PathVariable("id") long id, Model model){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);//httpstatus ok è
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = {"/active"})
    public String activeCustomers(Model model){
        List<Customer> results = (List<Customer>) customerService.getActiveCustomers();
        model.addAttribute("customers", results);
        return "customers/index";
    }

}
*/

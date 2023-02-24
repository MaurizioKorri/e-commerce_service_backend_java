package com.example.demo.demo1.controller.api;

import com.example.demo.demo1.dto.CustomerDto;
import com.example.demo.demo1.dto.SimpleCustomerDto;
import com.example.demo.demo1.entity.Customer;
import com.example.demo.demo1.mapper.CustomerMapper;
import com.example.demo.demo1.repository.CustomerRepository;
import com.example.demo.demo1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers/")
@CrossOrigin(origins = "*")
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerMapper customerMapper;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Customer>> getCustomers(Model model){
        List<Customer> results = (List<Customer>) customerRepository.findAll();
        model.addAttribute("customers", results);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    /*@GetMapping(value = {"/{id}"})
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") long id, Model model){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            return new ResponseEntity<CustomerDto>(customerMapper.toDto(customer.get()), HttpStatus.OK);//httpstatus ok è
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }*/

    @CrossOrigin(origins = "*")
    @PostMapping(value = {"/signup"})
    public Customer createCustomer(@RequestBody Customer newCustomer){ return customerRepository.save(newCustomer);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = {"/signin"})
    public ResponseEntity<SimpleCustomerDto> existsCustomer(@RequestBody Customer formCustomer){
        Optional<Customer> customer = customerRepository.findByCredentials(formCustomer.getEmail(), formCustomer.getPassword());

        if(customer.isPresent()){
            return new ResponseEntity<SimpleCustomerDto>(customerMapper.toDto(customer.get()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<SimpleCustomerDto>(new SimpleCustomerDto(), HttpStatus.OK);
            //return ResponseEntity.notFound().build();
        }
    }


    @PutMapping(value = {"/{id}"})
    public Customer updateCustomer(@PathVariable("id") long id, @RequestBody Customer newCustomer){

        return customerRepository.findById(id).map(
                customer -> {
                    customer.setFirstName(newCustomer.getFirstName());
                    customer.setLastName(newCustomer.getLastName());
                    customer.setActive(newCustomer.isActive());
                    customer.setCc(newCustomer.getCc());
                    customer.setAddresses(newCustomer.getAddresses());
                    return customerRepository.save(customer);
                }
        ).orElseGet(() -> {
           newCustomer.setId(id);
           return customerRepository.save(newCustomer);
        });


    }

    @DeleteMapping(value = {"/{id}"})
    public void deleteCustomer(@PathVariable("id") long id) {
        customerRepository.deleteById(id);
    }

}

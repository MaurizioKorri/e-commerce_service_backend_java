package com.example.demo.demo1.controller.api;

import com.example.demo.demo1.dto.CustomerDto;
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
    public ResponseEntity<List<Customer>> index(Model model){
        List<Customer> results = (List<Customer>) customerRepository.findAll();
        model.addAttribute("customers", results);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<CustomerDto> show(@PathVariable("id") long id, Model model){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            return new ResponseEntity<CustomerDto>(customerMapper.toDto(customer.get()), HttpStatus.OK);//httpstatus ok è
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(value = {"/", ""})
    public Customer create(@RequestBody Customer newCustomer){//prende l'oggetto che gli arriva dalla requestBody e con save
                                                              // lo scrive sul db, e oltre a ciò lo ritorna (tipo del method Customer)
                                                              //quindi possiamo controllare che sia andato tutto bene etc.
        return customerRepository.save(newCustomer);
        //su postman, il requestbody posso andare a metterglielo in Body (raw, JSON)
    }

    @PutMapping(value = {"/{id}"})
    public Customer update(@PathVariable("id") long id, @RequestBody Customer newCustomer){

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
    public void delete(@PathVariable("id") long id) {
        customerRepository.deleteById(id);
    }

}

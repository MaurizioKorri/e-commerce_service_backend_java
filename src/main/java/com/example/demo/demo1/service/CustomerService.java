package com.example.demo.demo1.service;

import com.example.demo.demo1.entity.Customer;
import com.example.demo.demo1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("CustomerService")
public class CustomerService{

    @Autowired
    CustomerRepository customerRepository;






    public List<Customer> getActiveCustomers() {
        return customerRepository.findByComplicatedQuery();
    }

}

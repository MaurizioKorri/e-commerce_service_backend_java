package com.example.demo.demo1.repository;

import com.example.demo.demo1.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Queue;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
                                            //lavoriamo su oggetti di tipo Customer identificati con un tipo long(Wrapper)
                                            //su spring è un interfaccia


    public List<Customer> findByActive(boolean status);

    @Query("from Customer where active = 1")
    public List<Customer> findByComplicatedQuery();
}

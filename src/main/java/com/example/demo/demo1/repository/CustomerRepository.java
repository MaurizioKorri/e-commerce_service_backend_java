package com.example.demo.demo1.repository;

import com.example.demo.demo1.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
                                            //lavoriamo su oggetti di tipo Customer identificati con un tipo long(Wrapper)
                                            //su spring è un interfaccia


    public List<Customer> findByActive(boolean status);

    @Query("from Customer where active = 1")
    public List<Customer> findByComplicatedQuery();


    @Query("SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password")
    Optional<Customer> findByCredentials(@Param("email") String email, @Param("password") String password);

}


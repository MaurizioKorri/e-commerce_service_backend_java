package com.example.demo.demo1.repository;

import com.example.demo.demo1.entity.Address;
import com.example.demo.demo1.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {

    @Transactional
    @Modifying
    @Query(value = "SELECT a from Address a WHERE a.customer.id = :customerid")
    public List<Address> findByCustomerrId(@Param("customerid")Long customerId);
}

package com.example.demo.demo1.repository;

import com.example.demo.demo1.entity.Address;
import com.example.demo.demo1.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

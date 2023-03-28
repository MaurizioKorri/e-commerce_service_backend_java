package com.example.demo.demo1.repository;

import com.example.demo.demo1.dto.ShoppingCartDto;
import com.example.demo.demo1.entity.CustomersProducts;
import com.example.demo.demo1.entity.ShoppingCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomersProductsRepository extends CrudRepository<CustomersProducts, Long> {

    @Query(value = "SELECT p from CustomersProducts p WHERE p.customer.id = :customerid")
    public List<CustomersProducts> findCustomerProducts(@Param("customerid") Long customerId);


    @Transactional
    @Modifying
    @Query(value = "INSERT into customersproducts (customer_id, product_id, quantity) VALUES (:customerid, :productid, :qty)",  nativeQuery = true)
    void addShoppingCartToCustomersProducts
            (@Param("customerid")Long id_customer, @Param("productid")Long id_product, @Param("qty") Integer quantity);
}

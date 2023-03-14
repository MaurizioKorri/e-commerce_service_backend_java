package com.example.demo.demo1.repository;

import com.example.demo.demo1.dto.ShoppingCartDto;
import com.example.demo.demo1.entity.ShoppingCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO shoppingcart_products (customer_id, product_id, quantity) VALUES (:customerid, :productid, :qty)", nativeQuery = true)
    public void saveInShoppingCart(@Param("productid")Long id_product, @Param("customerid")Long id_customer, @Param("qty")Integer quantity);


    @Query(value = "SELECT s from ShoppingCart s WHERE s.customer.id = :customerid")
    public List<ShoppingCart> findCustomerShoppingCart(@Param("customerid") Long customerId);


    @Transactional
    @Modifying
    @Query(value = "UPDATE ShoppingCart s SET s.quantity = :newQuantity WHERE s.product.id = :productid")
    public void updateQuantity(@Param("newQuantity")int newQuantity,@Param("productid") Long shoppingCart);
}

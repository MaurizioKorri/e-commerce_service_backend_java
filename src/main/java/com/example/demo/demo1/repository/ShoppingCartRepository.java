package com.example.demo.demo1.repository;

import com.example.demo.demo1.entity.ShoppingCartProduct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCartProduct, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO shoppingcart_products (customer_id, product_id, quantity) VALUES (:customerid, :productid, :qty)", nativeQuery = true)
    void saveInShoppingCart(@Param("productid")Long id_product, @Param("customerid")Long id_customer, @Param("qty")Integer quantity);


    @Query(value = "SELECT s from ShoppingCartProduct s WHERE s.customer.id = :customerid")
    List<ShoppingCartProduct> findCustomerShoppingCart(@Param("customerid") Long customerId);


    @Transactional
    @Modifying
    @Query(value = "UPDATE ShoppingCartProduct s SET s.quantity = :newQuantity WHERE s.product.id = :productid")
    void updateQuantity(@Param("newQuantity")int newQuantity,@Param("productid") Long shoppingCart);

    @Transactional
    @Modifying
    @Query(value = "DELETE  FROM shoppingcart_products s WHERE customer_id = :customerid", nativeQuery = true)
    void removeShoppingCart(@Param("customerid")Long customerId);
}

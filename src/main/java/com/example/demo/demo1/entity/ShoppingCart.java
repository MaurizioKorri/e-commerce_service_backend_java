package com.example.demo.demo1.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "shoppingcart_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    private Integer quantity = 1;
    public ShoppingCart(Product product, Customer customer, Integer quantity) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }
}

package com.example.demo.demo1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String productName;

    private String productDescr;

    @NotNull
    private Double productPrice;

    private Double productWeight;


    @JsonIgnore
    @ManyToMany(mappedBy = "boughtProducts")
    private List<Customer> buyers;




}

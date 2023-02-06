package com.example.demo.demo1.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String city;

    @NotNull
    private String streetAddress;

    @NotNull
    private String zipcode;

    @NotNull
    private String country;

    @NotNull
    private String addressType;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}

package com.example.demo.demo1.dto;

import com.example.demo.demo1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartProductDto {

    private Long id;
    private Product product;
    private Integer quantity = 1;
}

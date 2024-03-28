package com.example.ecommerce.product.dto;

import com.example.ecommerce.product.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class ProductDTO {

    private String name;
    private BigInteger price;
    private int stock;

    public ProductDTO(Product product){
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setStock(product.getStock());
    }
}


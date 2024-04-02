package com.example.ecommerce.product.dto;

import com.example.ecommerce.product.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigInteger;

public record ProductUpdate(
        String name,
        BigInteger price,
        @Positive
        int stock) {

    public Product toProduct(){
        Product product = new Product();
        product.setName(this.name);
        product.setPrice(this.price);
        product.setStock(this.stock);
        return product;
    }
}

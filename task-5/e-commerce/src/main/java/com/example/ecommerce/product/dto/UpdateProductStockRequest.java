package com.example.ecommerce.product.dto;

import com.example.ecommerce.product.Product;
import jakarta.validation.constraints.Positive;

import java.math.BigInteger;

public record UpdateProductStockRequest(
        @Positive
        int stock
) {

    public Product toProduct() {
        Product product = new Product();
        product.setStock(this.stock);
        return product;
    }
}

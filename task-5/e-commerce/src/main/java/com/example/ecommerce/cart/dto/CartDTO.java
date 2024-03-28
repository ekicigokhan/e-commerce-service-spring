package com.example.ecommerce.cart.dto;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.product.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
public class CartDTO {

    private int productCount;

    private BigInteger totalPrice;

    private List<Product> products;


    public CartDTO(Cart cart) {
        this.productCount = cart.getProductAmount();
        this.totalPrice = cart.getTotalPrice();
        this.products = cart.getProducts();
    }

}

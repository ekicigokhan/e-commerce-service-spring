package com.example.ecommerce.purchase.dto;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private BigInteger totalPrice;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany
    private List<Product> products;

    public OrderDTO() {
    }

    public OrderDTO(Purchase purchase) {
        this.totalPrice = purchase.getTotalPrice();
        this.customer = purchase.getCustomer();
        this.products = purchase.getProducts();
    }
}
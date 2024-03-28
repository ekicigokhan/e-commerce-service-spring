package com.example.ecommerce.cart;

import com.example.ecommerce.baseEntites.BaseEntity;
import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity {

    private int productAmount;

    private BigInteger totalPrice;

    @OneToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany
    private List<Product> products;


}

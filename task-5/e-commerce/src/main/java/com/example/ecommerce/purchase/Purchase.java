package com.example.ecommerce.purchase;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.baseEntites.BaseEntity;
import com.example.ecommerce.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends BaseEntity {

    private BigInteger totalPrice;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

}

package com.example.ecommerce.product;

import com.example.ecommerce.baseEntites.BaseEntity;
import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.cartItem.CartItem;
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
public class Product extends BaseEntity {

    private String name;

    private BigInteger price;

    @JsonIgnore
    private int stock;

    @OneToMany
    @JsonIgnore
    private List<CartItem> cartItems;

}

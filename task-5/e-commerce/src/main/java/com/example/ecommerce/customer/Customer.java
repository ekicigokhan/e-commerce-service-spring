package com.example.ecommerce.customer;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.baseEntites.BaseEntity;
import com.example.ecommerce.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Customer extends BaseEntity {

        private String username;

        private String email;

        @OneToOne
        @JoinColumn(name = "cart_id")
        private Cart cart;

        @OneToMany
        private List<Purchase> purchases;
    }

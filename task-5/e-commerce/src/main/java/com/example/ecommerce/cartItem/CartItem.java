package com.example.ecommerce.cartItem;

import com.example.ecommerce.baseEntites.BaseEntity;
import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntity {

    int quantity;

    BigInteger totalPrice;

    @ManyToOne
    private Product product;
}

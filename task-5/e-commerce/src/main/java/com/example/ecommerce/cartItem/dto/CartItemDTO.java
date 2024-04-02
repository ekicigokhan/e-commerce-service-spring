package com.example.ecommerce.cartItem.dto;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.product.Product;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {

    int quantity;
    private Product product;

    public CartItemDTO(CartItem cartItem) {
        this.quantity = cartItem.getQuantity();
    }
}

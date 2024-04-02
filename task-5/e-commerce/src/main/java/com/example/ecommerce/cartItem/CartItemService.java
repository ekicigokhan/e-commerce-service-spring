package com.example.ecommerce.cartItem;

import com.example.ecommerce.cartItem.dto.CartItemDTO;

public interface CartItemService {

    void save(CartItem cartItem);

    void removeCartItem(long id);

    CartItem getCartItem(long id);
}

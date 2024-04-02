package com.example.ecommerce.cart;

import com.example.ecommerce.cart.dto.AddProductToCartRequest;
import com.example.ecommerce.cart.dto.RemoveProductFromCartRequest;
import com.example.ecommerce.cart.dto.UpdateCartRequest;

public interface CartService {

    void save(Cart cart);

    Cart getCart(long id);

    void emptyCart(long id);

    void addProductToCart(AddProductToCartRequest addProductToCartRequest) throws Exception;

    void removeProductFromCart(long id, RemoveProductFromCartRequest removeProductFromCartRequest);

    void increaseProductFromCart(long id, UpdateCartRequest updateCartRequest);

    void decreaseProductFromCart(long id, UpdateCartRequest updateCartRequest);
}

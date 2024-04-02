package com.example.ecommerce.cartItem;

import com.example.ecommerce.cartItem.dto.CartItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void save(CartItem cartItem) {
        this.cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(long id) {
        this.cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem getCartItem(long id) {
        return this.cartItemRepository.findById(id).get();
    }
}

package com.example.ecommerce.cart;

import com.example.ecommerce.cart.dto.AddProductToCartRequest;
import com.example.ecommerce.cart.dto.CartDTO;
import com.example.ecommerce.cart.dto.RemoveProductFromCartRequest;
import com.example.ecommerce.cart.dto.UpdateCartRequest;
import com.example.ecommerce.shared.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public CartDTO getCart(@PathVariable long id){
        return new CartDTO(this.cartService.getCart(id));
    }



    @PutMapping("increaseProduct/{id}")
    Message increaseProductCart(@PathVariable long id, @RequestBody UpdateCartRequest updateCartRequest){
        this.cartService.increaseProductFromCart(id,updateCartRequest);
        return new Message("Cart updated !");
    }

    @PutMapping("decreaseProduct/{id}")
    Message decreaseProductFromCart(@PathVariable long id, @RequestBody UpdateCartRequest updateCartRequest){
        this.cartService.decreaseProductFromCart(id,updateCartRequest);
        return new Message("Cart updated !");
    }

    @DeleteMapping("/{id}")
    Message emptyCart(@PathVariable long id){
        this.cartService.emptyCart(id);
        return new Message("Cart emptied!");
    }

    @PostMapping
    Message addProductToCart(@RequestBody AddProductToCartRequest addProductToCartRequest) throws Exception{
        this.cartService.addProductToCart(addProductToCartRequest);
        return new Message("Products added to cart successfully!");
    }

    @DeleteMapping("removeProductFromCart/{id}")
    Message removeProductFromCart(@PathVariable long id, @RequestBody RemoveProductFromCartRequest removeProductFromCartRequest){
        this.cartService.removeProductFromCart(id,removeProductFromCartRequest);
        return new Message("Product has been removed.");
    }
}

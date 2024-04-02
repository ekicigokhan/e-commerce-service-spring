package com.example.ecommerce.cart;

import com.example.ecommerce.cart.dto.AddProductToCartRequest;
import com.example.ecommerce.cart.dto.RemoveProductFromCartRequest;
import com.example.ecommerce.cart.dto.UpdateCartRequest;
import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.cartItem.CartItemService;
import com.example.ecommerce.cartItem.dto.CartItemDTO;
import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.customer.CustomerService;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartItemService cartItemService;



    @Override
    public void save(Cart cart) {
        this.cartRepository.save(cart);
    }

    @Override
    public Cart getCart(long id) {
        return this.cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found."));
    }

    @Override
    public void emptyCart(long id) {
        Cart inDbCart = this.getCart(id);
        inDbCart.setProductAmount(0);
        inDbCart.setTotalPrice(BigInteger.ZERO);
        inDbCart.getCartItems().clear();
        this.cartRepository.save(inDbCart);
    }

    @Override
    public void addProductToCart(AddProductToCartRequest addProductToCartRequest) throws Exception {
        Customer customer = customerService.getCustomer(addProductToCartRequest.getCustomerId());
        Product product = productService.getProduct(addProductToCartRequest.getProductId());

        if (addProductToCartRequest.getQuantity() > product.getStock()){
            throw new Exception("Only " + product.getStock() + " of this product left in this seller");
        } else if (product.getStock() == 0) {
            throw new Exception("This product is finished. We will let you know ");
        }

        if (customer.getCart() == null) {
            Cart cart = new Cart();
            List<CartItem> cartItems = new ArrayList<>();
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(addProductToCartRequest.getQuantity());
            cartItem.setTotalPrice(product.getPrice());
            this.cartItemService.save(cartItem);
            cartItems.add(cartItem);
            product.setCartItems(cartItems);
            cart.setCartItems(cartItems);
            cart.setProductAmount(cartItems.size());
            cart.setCustomer(customer);
            cart.setTotalPrice(this.getTotalPrice(cart.getCartItems()));
            customer.setCart(cart);
            this.cartRepository.save(cart);
            this.customerService.addCustomer(customer);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(addProductToCartRequest.getQuantity());
            cartItem.setTotalPrice(product.getPrice());
            this.cartItemService.save(cartItem);
            customer.getCart().getCartItems().add(cartItem);
            customer.getCart().setProductAmount(customer.getCart().getCartItems().size());
            customer.getCart().setTotalPrice(this.getTotalPrice(customer.getCart().getCartItems()));
            this.cartRepository.save(customer.getCart());
            this.customerService.addCustomer(customer);
        }

    }

    @Override
    public void removeProductFromCart(long id, RemoveProductFromCartRequest removeProductFromCartRequest) {
        Cart inDbCart = this.getCart(id);
        for (CartItem cartItem : inDbCart.getCartItems()){
            if (cartItem.getId() == removeProductFromCartRequest.cartItemId()){
                inDbCart.getCartItems().remove(cartItem);
                this.cartItemService.save(cartItem);
                break;
            }
        }

    }

    @Override
    public void increaseProductFromCart(long id, UpdateCartRequest updateCartRequest) {

        Cart cart = this.getCart(id);
        CartItem cartItem = this.cartItemService.getCartItem(updateCartRequest.getCartItemId());
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItem.setTotalPrice(cartItem.getProduct().getPrice().multiply(BigInteger.valueOf((long)cartItem.getQuantity())));
        cart.setTotalPrice(this.getTotalPrice(cart.getCartItems()));
        this.cartItemService.save(cartItem);
    }

    @Override
    public void decreaseProductFromCart(long id, UpdateCartRequest updateCartRequest) {

        Cart cart = this.getCart(id);
        CartItem cartItem = this.cartItemService.getCartItem(updateCartRequest.getCartItemId());
        cartItem.setQuantity(cartItem.getQuantity() - 1);
        cartItem.setTotalPrice(cartItem.getProduct().getPrice().multiply(BigInteger.valueOf((long)cartItem.getQuantity())));
        cart.setTotalPrice(this.getTotalPrice(cart.getCartItems()));
        this.cartItemService.save(cartItem);
    }

    private BigInteger getTotalPrice(List<CartItem> cartItems){
        BigInteger total = BigInteger.ZERO;
        for (CartItem cartItem : cartItems){
            total = total.add(cartItem.getTotalPrice());
        }
        return total;
    }


}





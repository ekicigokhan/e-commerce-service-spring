package com.example.ecommerce.cart;

import com.example.ecommerce.cart.dto.AddProductToCartRequest;
import com.example.ecommerce.cart.dto.RemoveProductFromCartRequest;
import com.example.ecommerce.cart.dto.UpdateCartRequest;
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

    @Override
    public void save(Cart cart) {
        this.cartRepository.save(cart);
    }

    @Override
    public Cart getCart(long id) {
        return this.cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found."));
    }

    @Override
    public void updateCart(long id, UpdateCartRequest updateCartRequest) {

        Cart inDbCart = this.getCart(id);
        Product inDbProduct = productService.getProduct(updateCartRequest.getProductId());


    }

    @Override
    public void emptyCart(long id) {
        Cart inDbCart = this.getCart(id);
        inDbCart.setProductAmount(0);
        inDbCart.setTotalPrice(BigInteger.ZERO);
        inDbCart.getProducts().clear();
        this.cartRepository.save(inDbCart);
    }

    @Override
    public void addProductToCart(AddProductToCartRequest addProductToCartRequest) {
        Customer customer = customerService.getCustomer(addProductToCartRequest.getCustomerId());
        Product product = productService.getProduct(addProductToCartRequest.getProductId());

        if (customer.getCart() == null) {
            Cart cart = new Cart();
            List<Product> products = new ArrayList<>();
            products.add(product);
            cart.setProducts(products);
            cart.setProductAmount(addProductToCartRequest.getQuantity());
            cart.setCustomer(customer);
            customer.setCart(cart);
            this.cartRepository.save(cart);
            this.customerService.addCustomer(customer);
        } else {
            customer.getCart().getProducts().add(product);
            customer.getCart().setProductAmount(customer.getCart().getProductAmount() + addProductToCartRequest.getQuantity());
            this.cartRepository.save(customer.getCart());
            this.customerService.addCustomer(customer);
        }

    }

    @Override
    public void removeProductFromCart(long id, RemoveProductFromCartRequest removeProductFromCartRequest) {
        Cart inDbCart = this.getCart(id);
        Product product = productService.getProduct(removeProductFromCartRequest.productId());
        inDbCart.getProducts().remove(product);
        inDbCart.setProductAmount(inDbCart.getProductAmount() - 1);
        inDbCart.setTotalPrice(null);
        this.cartRepository.save(inDbCart);
    }


}





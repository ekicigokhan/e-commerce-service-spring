package com.example.ecommerce.customer.dto;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.purchase.Purchase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CustomerDTO {

    private String username;

    private String email;

    private Cart cart;

    private List<Purchase> purchases;

    public CustomerDTO(Customer customer){
      this.setUsername(customer.getUsername());
      this.setEmail(customer.getEmail());
      this.setCart(customer.getCart());
      this.setPurchases(customer.getPurchases());
    }
}

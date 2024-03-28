package com.example.ecommerce.customer.dto;

import com.example.ecommerce.customer.Customer;

public record CustomerCreate(

        String username,
        String email

) {
    public Customer toCustomer(){
        Customer customer = new Customer();
        customer.setUsername(this.username);
        customer.setEmail(this.email);
        return customer;
    }
}

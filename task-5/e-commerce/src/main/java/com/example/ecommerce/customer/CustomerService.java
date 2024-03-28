package com.example.ecommerce.customer;

public interface CustomerService {

    void addCustomer(Customer customer);

    Customer getCustomer(long id);
}

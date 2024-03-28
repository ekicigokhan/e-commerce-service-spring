package com.example.ecommerce.purchase.dto;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.purchase.Purchase;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
public class GetAllOrdersForCustomerResponse {

    private List<Purchase> purchases;

}

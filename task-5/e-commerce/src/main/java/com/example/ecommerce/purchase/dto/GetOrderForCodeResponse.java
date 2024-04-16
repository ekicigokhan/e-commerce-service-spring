package com.example.ecommerce.purchase.dto;

import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.purchase.Purchase;
import com.example.ecommerce.purchaseItem.PurchaseItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
public class GetOrderForCodeResponse {

    private Purchase purchase;

    public GetOrderForCodeResponse(Purchase purchase) {
        this.purchase = purchase;
    }
}

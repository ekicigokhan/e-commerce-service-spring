package com.example.ecommerce.cart.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartRequest {

    private long productId;

    private int quantity;
}

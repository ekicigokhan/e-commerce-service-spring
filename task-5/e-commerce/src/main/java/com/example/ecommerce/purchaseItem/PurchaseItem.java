package com.example.ecommerce.purchaseItem;

import com.example.ecommerce.baseEntites.BaseEntity;
import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem extends BaseEntity {

    private int productQuantity;

    private String productName;

    private BigInteger totalPrice;

    @ManyToOne
    @JsonIgnore
    private Purchase purchase;





}

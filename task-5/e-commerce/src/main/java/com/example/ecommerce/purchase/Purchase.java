package com.example.ecommerce.purchase;

import com.example.ecommerce.baseEntites.BaseEntity;
import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Purchase extends BaseEntity {

    private BigInteger totalPrice;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

}

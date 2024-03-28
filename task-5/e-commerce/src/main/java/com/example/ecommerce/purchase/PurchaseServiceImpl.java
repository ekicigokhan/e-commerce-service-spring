package com.example.ecommerce.purchase;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.cart.CartService;
import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.customer.CustomerService;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.purchase.dto.GetAllOrdersForCustomerResponse;
import com.example.ecommerce.purchase.dto.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;


    @Override
    public void placeOrder(PlaceOrderRequest placeOrderRequest) {
        Customer customer = this.customerService.getCustomer(placeOrderRequest.getCustomerId());
        Cart cart = customer.getCart();

        if (cart == null || cart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot be ordered.");
        }

        List<Product> products = new ArrayList<>();
        for (Product p : cart.getProducts()) {
            products.add(p);
        }

        Purchase purchase = new Purchase();
        purchase.setProducts(products);
        purchase.setTotalPrice(cart.getTotalPrice());
        purchase.setCustomer(customer);
        customer.getPurchases().add(purchase);
        this.purchaseRepository.save(purchase);

        this.cartService.emptyCart(cart.getId());
    }

    @Override
    public Purchase getOrderForCode(long id) {
        return this.purchaseRepository.findById(id).orElseThrow(() -> new RuntimeException("Order is not found."));
    }

    @Override
    public List<GetAllOrdersForCustomerResponse> getAllOrdersForCustomerResponse(long id) {
        Customer customer = this.customerService.getCustomer(id);

        if (customer.getPurchases() == null || customer.getPurchases().isEmpty()) {
            throw new RuntimeException("Order is not found.");
        }

        List<GetAllOrdersForCustomerResponse> getAllOrdersForCustomerResponses = new ArrayList<>();
        GetAllOrdersForCustomerResponse getAllOrdersForCustomerResponse = new GetAllOrdersForCustomerResponse();
        getAllOrdersForCustomerResponse.setPurchases(customer.getPurchases());
        getAllOrdersForCustomerResponses.add(getAllOrdersForCustomerResponse);

        return getAllOrdersForCustomerResponses;

    }
}

package com.example.ecommerce.purchase;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.cart.CartService;
import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.customer.CustomerService;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductService;
import com.example.ecommerce.purchase.dto.GetAllOrdersForCustomerResponse;
import com.example.ecommerce.purchase.dto.PlaceOrderRequest;
import com.example.ecommerce.purchaseItem.PurchaseItem;
import com.example.ecommerce.purchaseItem.PurchaseItemService;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseItemService purchaseItemService;


    @Override
    public void placeOrder(PlaceOrderRequest placeOrderRequest) throws Exception {
        Customer customer = this.customerService.getCustomer(placeOrderRequest.getCustomerId());
        Cart cart = customer.getCart();

        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot be ordered.");
        }

        for (CartItem c : cart.getCartItems()){
            if (c.getQuantity() > c.getProduct().getStock()){
                throw new Exception("Only " + c.getProduct().getStock() + " of this product left in this seller");
            }
         }

        Purchase purchase = new Purchase();
        this.purchaseRepository.save(purchase);

        List<PurchaseItem> purchaseItems = new ArrayList<>();
        for (CartItem item : cart.getCartItems()) {
            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setProductQuantity(item.getQuantity());
            purchaseItem.setTotalPrice(item.getTotalPrice());
            purchaseItem.setProductName(item.getProduct().getName());
            purchaseItem.setPurchase(purchase);
            purchaseItems.add(purchaseItem);
            this.purchaseItemService.save(purchaseItem);
        }

        purchase.setPurchaseItems(purchaseItems);
        purchase.setTotalPrice(this.getTotalPricePurchases(purchaseItems));
        purchase.setCustomer(customer);
        customer.getPurchases().add(purchase);
        this.purchaseRepository.save(purchase);

        for (CartItem c : cart.getCartItems()) {
            c.getProduct().setStock(c.getProduct().getStock() - c.getQuantity());
            this.productService.createProduct(c.getProduct());
        }

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

    public BigInteger getTotalPricePurchases(List<PurchaseItem> purchaseItems) {
        BigInteger total = BigInteger.ZERO;
        for (PurchaseItem p : purchaseItems) {
            total = total.add(p.getTotalPrice());
        }
        return total;
    }
}



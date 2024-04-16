package com.example.ecommerce.purchase;

import com.example.ecommerce.purchase.dto.GetAllOrdersForCustomerResponse;
import com.example.ecommerce.purchase.dto.PlaceOrderRequest;

import java.util.List;

public interface PurchaseService {

    void placeOrder(PlaceOrderRequest placeOrderRequest) throws Exception;

    Purchase getOrderForCode(long id);

    List<GetAllOrdersForCustomerResponse> getAllOrdersForCustomerResponse(long id);
}

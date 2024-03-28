package com.example.ecommerce.purchase;

import com.example.ecommerce.purchase.dto.GetAllOrdersForCustomerResponse;
import com.example.ecommerce.purchase.dto.OrderDTO;
import com.example.ecommerce.purchase.dto.PlaceOrderRequest;
import com.example.ecommerce.shared.Message;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    Message placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest){
        this.purchaseService.placeOrder(placeOrderRequest);
        return new Message("Order placed.");
    }

    @GetMapping("/{id}")
    OrderDTO getOrderForCode(@PathVariable long id){
        return new OrderDTO(this.purchaseService.getOrderForCode(id));
    }

    @GetMapping("getAllOrdersForCustomer/{id}")
    List<GetAllOrdersForCustomerResponse> getAllOrdersForCustomer(@PathVariable long id){
        return this.purchaseService.getAllOrdersForCustomerResponse(id);
    }





}

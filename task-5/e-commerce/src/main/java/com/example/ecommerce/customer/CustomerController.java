package com.example.ecommerce.customer;

import com.example.ecommerce.customer.dto.CustomerCreate;
import com.example.ecommerce.customer.dto.CustomerDTO;
import com.example.ecommerce.shared.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    Message addCustomer(@RequestBody CustomerCreate customerCreate){
        this.customerService.addCustomer(customerCreate.toCustomer());
        return new Message("Customer registration is successful");
    }

    @GetMapping("/{id}")
    CustomerDTO getCustomer(@PathVariable long id){
        return new CustomerDTO(this.customerService.getCustomer(id));
    }

}

package com.example.ecommerce.purchaseItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {

    @Autowired
    PurchaseItemRepository purchaseItemRepository;
    @Override
    public void save(PurchaseItem purchaseItem) {
        this.purchaseItemRepository.save(purchaseItem);
    }
}

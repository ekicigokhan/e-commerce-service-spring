package com.example.ecommerce.product;

import com.example.ecommerce.product.dto.UpdateProductStockRequest;

public interface ProductService {

    Product getProduct(long id);
    void createProduct(Product product);
    void updateProduct(long id, Product product);
    void updateProductStock(long id, UpdateProductStockRequest updateProductStockRequest);
    void deleteProduct(long id);

}

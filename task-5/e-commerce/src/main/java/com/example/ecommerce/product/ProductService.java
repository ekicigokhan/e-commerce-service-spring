package com.example.ecommerce.product;

public interface ProductService {

    Product getProduct(long id);

    void createProduct(Product product);
    void updateProduct(long id ,Product product);
    void deleteProduct(long id);

}

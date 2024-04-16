package com.example.ecommerce.product;

import com.example.ecommerce.product.dto.UpdateProductStockRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found !"));
    }

    @Override
    public void createProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void updateProduct(long id, Product product) {
        Product inDb = getProduct(id);
        inDb.setName(product.getName());
        inDb.setPrice(product.getPrice());
        this.productRepository.save(inDb);
    }

    @Override
    public void updateProductStock(long id, UpdateProductStockRequest updateProductStockRequest) {
        Product inDb = this.getProduct(id);
        inDb.setStock(updateProductStockRequest.stock());
        this.productRepository.save(inDb);
    }

    @Override
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }
}

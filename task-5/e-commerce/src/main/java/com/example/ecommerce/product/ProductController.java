package com.example.ecommerce.product;

import com.example.ecommerce.product.dto.ProductCreate;
import com.example.ecommerce.product.dto.ProductDTO;
import com.example.ecommerce.product.dto.ProductUpdate;
import com.example.ecommerce.shared.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    ProductDTO getProduct(@PathVariable long id){
        return new ProductDTO(this.productService.getProduct(id));
    }

    @PostMapping
    Message createProduct(@RequestBody ProductCreate productCreate){
        this.productService.createProduct(productCreate.toProduct());
        return new Message("Product added successfully");
    }

    @PutMapping("/{id}")
    Message updateProduct(@PathVariable long id, @RequestBody ProductUpdate productUpdate){
        this.productService.updateProduct(id,productUpdate.toProduct());
        return new Message("Product updated successfully");
    }

    @DeleteMapping("/{id}")
    Message deleteProduct(@PathVariable long id){
        this.productService.deleteProduct(id);
        return new Message("Product deleted.");
    }




}

package com.abhinav.productservicesprojectsst.controllers;

import com.abhinav.productservicesprojectsst.models.Category;
import com.abhinav.productservicesprojectsst.models.Product;
import com.abhinav.productservicesprojectsst.services.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
    ProductService productServices;
    public ProductController(ProductService productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productServices.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productServices.getProductById(id);
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories() {
        return productServices.getAllCategories();
    }
    @GetMapping("/products/categories/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productServices.getProductsByCategory(category);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productServices.createProduct(product);
    }

    @PutMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        return productServices.updateProduct(id, product);
    }

    @DeleteMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        return productServices.deleteProduct(id);
    }
}

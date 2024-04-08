package com.abhinav.productservicesprojectsst.services;

import com.abhinav.productservicesprojectsst.models.Category;
import com.abhinav.productservicesprojectsst.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
    List<Product> getProductsByCategory(String category);
    List<Category> getAllCategories();
    Product createProduct(Product product);
    String updateProduct(int id, Product product);
    String deleteProduct(int id);
}

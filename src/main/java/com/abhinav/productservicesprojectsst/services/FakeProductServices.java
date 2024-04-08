package com.abhinav.productservicesprojectsst.services;

import com.abhinav.productservicesprojectsst.dtos.FakeStoreProductDTO;
import com.abhinav.productservicesprojectsst.models.Category;
import com.abhinav.productservicesprojectsst.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductServices implements ProductService{

    private final RestTemplate restTemplate = new RestTemplate();
    //Allows us to send API requests to any URL

    @Override
    public List<Product> getAllProducts() {
        String url = "https://fakestoreapi.com/products" ;

        ParameterizedTypeReference<List<FakeStoreProductDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<FakeStoreProductDTO>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                responseType
        );

        List<FakeStoreProductDTO> fakeStoreProductDTOS = responseEntity.getBody();

        List<Product> products = new ArrayList<>();

        if (fakeStoreProductDTOS == null) {
            return new ArrayList<>();
        }
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOS) {
            Product product = new Product();
            product.setName(fakeStoreProductDTO.getTitle());
            product.setDescription(fakeStoreProductDTO.getDescription());
            product.setPrice(fakeStoreProductDTO.getPrice());
            product.setImage(fakeStoreProductDTO.getImage());
            product.setCategory(new Category());
            product.getCategory().setName(fakeStoreProductDTO.getCategory());
            products.add(product);
        }

        return products;
    }

    @Override
    public Product getProductById(int id) {
        String url = "https://fakestoreapi.com/products/" + id;

        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(url, FakeStoreProductDTO.class);
        if(fakeStoreProductDTO == null) {
            return new Product();
        }

        Product product = new Product();
        product.setName(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImage(fakeStoreProductDTO.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());

        return product;
    }
    @Override
    public List<Category> getAllCategories() {
        String url = "https://fakestoreapi.com/products/categories";

        ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<String>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                responseType
        );

        List<String> categories = responseEntity.getBody();

        List<Category> categoryList = new ArrayList<>();

        if (categories == null) {
            return new ArrayList<>();
        }

        for (String category : categories) {
            Category category1 = new Category();
            category1.setName(category);
            category1.setId(categories.indexOf(category));
            categoryList.add(category1);
        }

        return categoryList;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {

        String url = "https://fakestoreapi.com/products/category/" + category;

        return getProducts(url);
    }

    public List<Product> getProducts(String url) {
        ParameterizedTypeReference<List<FakeStoreProductDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<FakeStoreProductDTO>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                responseType
        );

        List<FakeStoreProductDTO> fakeStoreProductDTOS = responseEntity.getBody();

        List<Product> products = new ArrayList<>();

        if(fakeStoreProductDTOS == null) {
            return new ArrayList<>();
        }


        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOS) {
            Product product = new Product();
            product.setName(fakeStoreProductDTO.getTitle());
            product.setDescription(fakeStoreProductDTO.getDescription());
            product.setPrice(fakeStoreProductDTO.getPrice());
            product.setImage(fakeStoreProductDTO.getImage());
            product.setCategory(new Category());
            product.getCategory().setName(fakeStoreProductDTO.getCategory());
            products.add(product);
        }

        return products;
    }

    @Override
    public Product createProduct(Product product) {
        String url = "https://fakestoreapi.com/products";

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getName());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        fakeStoreProductDTO.setImage(product.getImage());

        restTemplate.postForObject(url, fakeStoreProductDTO, FakeStoreProductDTO.class);

        return product;
    }
    @Override
    public String updateProduct(int id, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getName());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        fakeStoreProductDTO.setImage(product.getImage());

        String url = "https://fakestoreapi.com/products/" + id;

        restTemplate.put(url, fakeStoreProductDTO);

        return "Product with id " + id + " updated successfully.";
    }

    @Override
    public String deleteProduct(int id) {
        String url = "https://fakestoreapi.com/products/" + id;

        restTemplate.delete(url);

        return "Product with id " + id + " deleted successfully.";
    }
}

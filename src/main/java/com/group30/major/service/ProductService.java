package com.group30.major.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group30.major.model.Category;
import com.group30.major.model.Product;
import com.group30.major.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    public List<Product> getAllProductByQuery(String query) { 
       return productRepository.findByNameContainingIgnoreCase(query); 
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public void removeProductById(long id) {
        productRepository.deleteById(id);
    }
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }
    public List<Product> getAllProductsByCategoryId(int id) {
        return productRepository.findAllByCategory_Id(id);
    }
}

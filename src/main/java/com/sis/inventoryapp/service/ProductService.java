package com.sis.inventoryapp.service;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProducts(List<Product> productList) {
        productList
                .forEach(p -> productRepository.save(p));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

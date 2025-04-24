package com.sis.inventoryapp.service;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.engine.RulesEngineV1;
import com.sis.inventoryapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private RulesEngineV1 rulesEngineV1;
    @Autowired
    private ProductRepository productRepository;

    public void addProducts(List<Product> productList) {
        productList
                .forEach(p -> productRepository.save(p));
    }

    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return rulesEngineV1.run(productList);
    }
}

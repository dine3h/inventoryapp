package com.sis.inventoryapp.controller;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return null;
    }

    @PostMapping("/products")
    public List<Product> addProducts(List<Product> productList){
        return null;
    }
}

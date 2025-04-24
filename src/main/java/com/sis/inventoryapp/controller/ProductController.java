package com.sis.inventoryapp.controller;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public void addProducts(@RequestBody List<Product> productList){
        productService.addProducts(productList);
    }
}

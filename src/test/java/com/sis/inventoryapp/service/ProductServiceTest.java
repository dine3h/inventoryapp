package com.sis.inventoryapp.service;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService = new ProductService();

    private static Product product;
    private static Product product1;
    private static Product product2;
    private static List<Product> productList;

    @BeforeAll
    static void setup() {
        productList = new ArrayList<>();
        product = new Product("Aged Brie", 1, 1, Instant.now());
        product1 = new Product("Backstage Passes", 1, 1, Instant.now());
        product2 = new Product("General Item", 1, 1, Instant.now());
    }

    @Test
    void shouldReturnListOfProducts_whenProductsAddedToDbSuccess() {
       when(productRepository.saveAll(anyList())).thenReturn(productList);
       List<Product> returnedProductList = productService.addProducts(productList);

       Assertions.assertEquals(3, returnedProductList.size());
    }

    @Test
    void shouldReturnListOfProducts_whenProductsFetchedFromDb() {
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> returnedProductList = productService.getAllProducts();

        Assertions.assertEquals(3, returnedProductList.size());
    }
}
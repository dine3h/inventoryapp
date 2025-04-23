package com.sis.inventoryapp.controller;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService mockProductService;

    private static Product product;
    private static Product product1;
    private static Product product2;
    private static List<Product> productList;

    @BeforeAll
    static void setup(){
        product = new Product("Aged Brie", 1, 1);
        product1 = new Product("Backstage Passes", 1, 1);
        product2 = new Product("Backstage Passes", 1, 1);

        productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        productList.add(product2);
    }

    @Test
    public void shouldReturnProductList_whenFetchingAllProductsSuccess() throws Exception {
        when(mockProductService.getAllProducts()).thenReturn(productList);

        this.mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Aged Brie"))
                .andExpect(jsonPath("$[0].sellInDate").value(1))
                .andExpect(jsonPath("$[0].quality").value(1))
                .andExpect(jsonPath("$[1].name").value("Backstage Passes"))
                .andExpect(jsonPath("$[1].sellInDate").value(1))
                .andExpect(jsonPath("$[1].quality").value(1))
                .andExpect(jsonPath("$[2].name").value("Backstage Passes"))
                .andExpect(jsonPath("$[2].sellInDate").value(1))
                .andExpect( jsonPath("$[2].quality").value(1));
    }

    @Test
    void shouldReturnProductsList_whenProductsAddedToDbSuccess() throws Exception {
        String jsonInput = "[{\"name\":\"Aged Brie\",\"sellInDate\":1\"quality\":1},{\"name\":\"Backstage Passes\",\"sellInDate\":1\"quality\":1}," +
                                "{\"name\":\"Backstage Passes\",\"sellInDate\":1\"quality\":1}]";

        when(mockProductService.addProducts(anyList())).thenReturn(productList);
        this.mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$[0].name").value("Aged Brie"))
                .andExpect( jsonPath("$[0].sellInDate").value(1))
                .andExpect(jsonPath("$[0].quality").value(1))
                .andExpect(jsonPath("$[1].name").value("Backstage Passes"))
                .andExpect(jsonPath("$[1].sellInDate").value(1))
                .andExpect(jsonPath("$[1].quality").value(1))
                .andExpect(jsonPath("$[2].name").value("Backstage Passes"))
                .andExpect(jsonPath("$[2].sellInDate").value(1))
                .andExpect(jsonPath("$[2].quality").value(1));
    }
}
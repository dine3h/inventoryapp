package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.utils.TimeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RulesEngineV1Test {

    @Autowired
    private RulesEngineV1 rulesEngineV1;

    @Test
    void shouldReturnAggregatedUpdatedList_whenRunSuccess() {
        List<Product> productList = new ArrayList<>();
        Product product = new Product("Aged Brie", 7, 4);
        Product product1 = new Product("Backstage passes", 4, 4);
        Product product2 = new Product("Normal Product", 7, 4);

        Instant pastDate = TimeUtils.setHistoricCreationDate(2);

        product.setCreatedOn(pastDate);
        product1.setCreatedOn(pastDate);
        product2.setCreatedOn(pastDate);

        productList.add(product);
        productList.add(product1);
        productList.add(product2);

        List<Product> updatedProductList = rulesEngineV1.run(productList);

        Product expectedProduct = new Product("Aged Brie", 5, 6);
        Product expectedProduct1 = new Product("Backstage passes", 2, 10);
        Product expectedProduct2 = new Product("Normal Product", 5, 2);

        Assertions.assertEquals(expectedProduct, updatedProductList.getFirst());
        Assertions.assertEquals(expectedProduct1, updatedProductList.get(1));
        Assertions.assertEquals(expectedProduct2, updatedProductList.get(2));
    }
}
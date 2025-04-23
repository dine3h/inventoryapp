package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

class SellInRuleTest {

    private Rule sellInRule = new SellInRule();

    @Test
    void shouldReturnDecreasedSellInDate_whenCurrentDateIsDifferentToCreatedDate(){
        List<Product> productList = new ArrayList<>();

        Product product = new Product("Aged Brie", 7, 1);
        Product product1 = new Product("Backstage Passes", 5, 1);
        Product product2 = new Product("General Item", 1, 1);

        Instant instant = Instant.now();
        Instant pastInstant = instant.minus(Period.ofDays(2));

        product.setCreatedOn(pastInstant);
        product1.setCreatedOn(pastInstant);
        product2.setCreatedOn(pastInstant);
        productList.add(product);
        productList.add(product1);
        productList.add(product2);

        List<Product> updatedProductList = sellInRule.execute(productList);

        Assertions.assertEquals(5, updatedProductList.get(0).getSellInDate());
        Assertions.assertEquals(3, updatedProductList.get(1).getSellInDate());
        Assertions.assertEquals(-1, updatedProductList.get(2).getSellInDate());
    }

    @Test
    void shouldReturnSameSellInDate_whenCurrentDateIsSameAsCreatedDate() {
        List<Product> productList = new ArrayList<>();

        Product product = new Product("Aged Brie", 7, 1, Instant.now());
        Product product1 = new Product("Backstage Passes", 5, 1, Instant.now());
        Product product2 = new Product("General Item", 3, 1, Instant.now());
        productList.add(product);
        productList.add(product1);
        productList.add(product2);

        List<Product> updatedProductList = sellInRule.execute(productList);

        Assertions.assertEquals(7, updatedProductList.get(0).getSellInDate());
        Assertions.assertEquals(5, updatedProductList.get(1).getSellInDate());
        Assertions.assertEquals(3, updatedProductList.get(2).getSellInDate());
    }
}
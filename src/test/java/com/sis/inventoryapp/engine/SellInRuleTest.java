package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

class SellInRuleTest {

    private SellInRule sellInRule = new SellInRule();

    private static Product product;
    private static Product product1;
    private static Product product2;
    private static List<Product> nowProductList;
    private static List<Product> oldProductList;

    @BeforeAll
    static void setup() {
        nowProductList = new ArrayList<>();
        oldProductList = new ArrayList<>();

        product = new Product("Aged Brie", 1, 1, Instant.now());
        product1 = new Product("Backstage Passes", 1, 1, Instant.now());
        product2 = new Product("Backstage Passes", 1, 1, Instant.now());
        nowProductList.add(product);
        nowProductList.add(product1);
        nowProductList.add(product2);

        //Calculates two days earlier timestamp to simulate historic creation of inventory
        Instant instant = Instant.now();
        Instant pastInstant = instant.minus(Period.ofDays(2));

        product.setCreatedOn(pastInstant);
        product1.setCreatedOn(pastInstant);
        product2.setCreatedOn(pastInstant);
        oldProductList.add(product);
        oldProductList.add(product1);
        oldProductList.add(product2);
    }

    @Test
    void shouldReturnDecreasedSellInDate_whenCurrentDateIsDifferentToCreatedDate(){
        List<Product> updatedProductList = sellInRule.execute(oldProductList);
        int oldSellInDate = oldProductList.get(0).getSellInDate();
        int newSellInDate = updatedProductList.get(0).getSellInDate();
        int diffInDays = oldSellInDate - newSellInDate;

        Assertions.assertEquals(2, diffInDays);
    }

    @Test
    void shouldReturnSameSellInDate_whenCurrentDateIsSameAsCreatedDate() {
        List<Product> updatedProductList = sellInRule.execute(oldProductList);
        int oldSellInDate = oldProductList.get(0).getSellInDate();
        int newSellInDate = updatedProductList.get(0).getSellInDate();
        int diffInDays = oldSellInDate - newSellInDate;

        Assertions.assertEquals(0, diffInDays);
    }
}
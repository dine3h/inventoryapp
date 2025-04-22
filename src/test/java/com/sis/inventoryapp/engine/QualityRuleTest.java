package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

class QualityRuleTest {

    QualityRule qualityRule = new QualityRule();

    private static Product product;
    private static Product product1;
    private static Product product2;
    private static List<Product> productList;

    @BeforeAll
    static void setup() {
        productList = new ArrayList<>();
        product = new Product("Aged Brie", 1, 1);
        product1 = new Product("Backstage Passes", 1, 1);
        product2 = new Product("General Item", 1, 1);


        //Calculates one day earlier timestamp to simulate historic creation of inventory
        Instant instant = Instant.now();
        Instant pastInstant = instant.minus(Period.ofDays(1));

        product.setCreatedOn(pastInstant);
        product1.setCreatedOn(pastInstant);
        product2.setCreatedOn(pastInstant);

        productList.add(product);
        productList.add(product1);
        productList.add(product2);
    }

    @Test
    void shouldReturnDecreaseInQualityForGeneralProducts_whenDateIsDifferentToCreatedDate() {
        List<Product> updatedProductList = qualityRule.execute(productList);
        int updatedProductQuality = updatedProductList.get(2).getQuality();

        Assertions.assertEquals(0, updatedProductQuality);
    }

    @Test
    void shouldReturnIncreaseInQuality_whenProductIsBrieAndQualityNotGreaterThanFifty() {
        List<Product> updatedProductList = qualityRule.execute(productList);
        int updatedProductQuality = updatedProductList.get(0).getQuality();

        Assertions.assertEquals(2, updatedProductQuality);
    }

    @Test
    void shouldReturnNoIncreaseInQuality_whenProductIsBrieAndQualityIsEqualToFifty() {
        product.setQuality(50);
        productList.set(0, product);

        List<Product> updatedProductList = qualityRule.execute(productList);
        int updatedProductQuality = updatedProductList.get(0).getQuality();

        Assertions.assertEquals(50, updatedProductQuality);
    }

    @Test
    void shouldReturnTwoFoldIncreaseInQuality_whenBackStagePassesAndSellInDateLessThanTenDays() {
        product1.setSellInDate(8);
        productList.set(1, product1);

        List<Product> updatedProductList = qualityRule.execute(productList);
        int updatedProductQuality = updatedProductList.get(1).getQuality();

        Assertions.assertEquals(2, updatedProductQuality);
    }

    @Test
    void shouldReturnThreeFoldIncreaseInQuality_whenBackStagePassesAndSellInDateLessThanFiveDays() {
        product1.setSellInDate(4);
        productList.set(1, product1);

        List<Product> updatedProductList = qualityRule.execute(productList);
        int updatedProductQuality = updatedProductList.get(1).getQuality();

        Assertions.assertEquals(3, updatedProductQuality);
    }

    @Test
    void shouldReturnQualityIsZero_whenBackStagePassesSellInDateIsZero() {
        product1.setSellInDate(0);
        productList.set(1, product1);

        List<Product> updatedProductList = qualityRule.execute(productList);
        int updatedProductQuality = updatedProductList.get(1).getQuality();

        Assertions.assertEquals(0, updatedProductQuality);
    }
}
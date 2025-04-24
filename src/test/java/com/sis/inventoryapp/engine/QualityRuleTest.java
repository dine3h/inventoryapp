package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.utils.TimeUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class QualityRuleTest {

    @Autowired
    private QualityRule qualityRule;

    private static List<Product> productList;

    @BeforeEach
    void init() {
        productList = new ArrayList<>();
    }

    @AfterEach
    void cleanup() {
        productList.clear();
    }


    @Test
    void shouldReturnIncreaseInQuality_whenProductIsBrieAndQualityNotGreaterThanFifty() {
        Product product = new Product("Aged Brie", 7, 4);

        Instant pastInstant = TimeUtils.setHistoricCreationDate(2);

        product.setCreatedOn(pastInstant);
        productList.add(product);
        List<Product> updatedProductList = qualityRule.execute(productList);

        Assertions.assertEquals(6, updatedProductList.getFirst().getQuality());
    }

    @Test
    void shouldReturnNoChangeInQuality_whenProductIsBrieAndQualityEqualsFifty() {
        Product product = new Product("Aged Brie", 7, 50);

        Instant pastInstant = TimeUtils.setHistoricCreationDate(2);
        product.setCreatedOn(pastInstant);

        productList.add(product);
        List<Product> updatedProductList = qualityRule.execute(productList);

        Assertions.assertEquals(50, updatedProductList.getFirst().getQuality());
    }

    @Test
    void shouldReturnNormalIncreaseInQuality_whenBackStagePassesAndSellInDateGreaterThanTenDays() {
        Product product = new Product("Backstage passes", 12, 10);

        Instant pastInstant = TimeUtils.setHistoricCreationDate(2);
        product.setCreatedOn(pastInstant);

        productList.add(product);
        List<Product> updatedProductList = qualityRule.execute(productList);

        Assertions.assertEquals(12, updatedProductList.getFirst().getQuality());
    }

    @Test
    void shouldReturnTwoFoldIncreaseInQuality_whenBackStagePassesAndSellInDateLessThanTenDays() {
        Product product = new Product("Backstage passes", 7, 10);

        Instant pastInstant = TimeUtils.setHistoricCreationDate(2);
        product.setCreatedOn(pastInstant);

        productList.add(product);
        List<Product> updatedProductList = qualityRule.execute(productList);

        Assertions.assertEquals(14, updatedProductList.getFirst().getQuality());
    }

    @Test
    void shouldReturnThreeFoldIncreaseInQuality_whenBackStagePassesAndSellInDateLessThanFiveDays() {
        Product product = new Product("Backstage passes", 4, 10);

        Instant pastInstant = TimeUtils.setHistoricCreationDate(2);
        product.setCreatedOn(pastInstant);

        productList.add(product);
        List<Product> updatedProductList = qualityRule.execute(productList);

        Assertions.assertEquals(16, updatedProductList.getFirst().getQuality());
    }

    @Test
    void shouldReturnZeroQuality_whenBackStagePassesAndSellInDateIsExpired() {
        Product product = new Product("Backstage passes", 0, 10);

        Instant pastInstant = TimeUtils.setHistoricCreationDate(2);
        product.setCreatedOn(pastInstant);

        productList.add(product);
        List<Product> updatedProductList = qualityRule.execute(productList);

        Assertions.assertEquals(0, updatedProductList.getFirst().getQuality());
    }

    @Test
    void shouldReturnNoChangeInQuality_whenSulfuras() {
        Product product = new Product("Sulfuras", 5, 10);

        Instant pastInstant = TimeUtils.setHistoricCreationDate(2);
        product.setCreatedOn(pastInstant);

        productList.add(product);
        List<Product> updatedProductList = qualityRule.execute(productList);

        Assertions.assertEquals(10, updatedProductList.getFirst().getQuality());
    }

    @Test
    void shouldReturnNormalDecreaseInQuality_whenNormalProductAndDateIsDifferentToCreatedDate() {
        Product product = new Product("Normal Product", 5, 10);

        Instant pastInstant = TimeUtils.setHistoricCreationDate(2);
        product.setCreatedOn(pastInstant);

        productList.add(product);
        List<Product> updatedProductList = qualityRule.execute(productList);

        Assertions.assertEquals(8, updatedProductList.getFirst().getQuality());
    }

    @Test
    void shouldReturnNoChangeInQuality_whenNormalProductAndDateSameAsCreatedDate() {
        Product product = new Product("Normal Product", 5, 10, Instant.now());

        productList.add(product);
        List<Product> updatedProductList = qualityRule.execute(productList);

        Assertions.assertEquals(10, updatedProductList.getFirst().getQuality());
    }
}
package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RulesEngineV1 {

    @Autowired
    private SellInRule sellInRule;
    @Autowired
    private QualityRule qualityRule;

    public List<Product> run(List<Product> productList) {
        return qualityRule.execute(sellInRule.execute(productList));
    }
}

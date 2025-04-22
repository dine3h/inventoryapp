package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QualityRule implements Rule{

    public List<Product> execute(List<Product> productList) {

        return null;
    }
}

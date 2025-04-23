package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;

import java.util.List;

public interface Rule {
    List<Product> execute(List<Product> productList);

}

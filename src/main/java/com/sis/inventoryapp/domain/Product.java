package com.sis.inventoryapp.domain;

import java.util.Objects;

public class Product {

    private String name;
    private int sellInDate;
    private int Quality;

    public Product(String name, int sellInDate, int quality) {
        this.name = name;
        this.sellInDate = sellInDate;
        Quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellInDate() {
        return sellInDate;
    }

    public int getQuality() {
        return Quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return sellInDate == product.sellInDate && Quality == product.Quality && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellInDate, Quality);
    }
}

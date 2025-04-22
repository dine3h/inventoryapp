package com.sis.inventoryapp.domain;

import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Objects;

public class Product {

    private String name;
    private int sellInDate;
    private int quality;
    @CreationTimestamp
    private Instant createdOn;

    public Product(String name, int sellInDate, int quality) {
        this.name = name;
        this.sellInDate = sellInDate;
        this.quality = quality;
    }

    public Product(String name, int sellInDate, int quality, Instant instant) {
        this.name = name;
        this.sellInDate = sellInDate;
        this.quality = quality;
        this.createdOn = instant;
    }

    public String getName() {
        return name;
    }

    public int getSellInDate() {
        return sellInDate;
    }

    public void setSellInDate(int sellInDate) {
        this.sellInDate = sellInDate;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return sellInDate == product.sellInDate && quality == product.quality && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellInDate, quality);
    }
}

package com.sis.inventoryapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;


import java.time.Instant;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private long sellInDate;
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

    public long getSellInDate() {
        return sellInDate;
    }

    public void setSellInDate(long sellInDate) {
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

package com.sis.inventoryapp.repository;

import com.sis.inventoryapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

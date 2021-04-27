package com.com.online.store.online.store.repository;

import com.com.online.store.online.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

package com.com.online.store.online.store.repository;

import com.com.online.store.online.store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

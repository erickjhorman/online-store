package com.com.online.store.online.store.repository;

import com.com.online.store.online.store.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}

package com.com.online.store.online.store.serviceimpl;

import com.com.online.store.online.store.model.Order;
import com.com.online.store.online.store.model.Payment;
import com.com.online.store.online.store.model.Product;
import com.com.online.store.online.store.model.SubProduct;
import com.com.online.store.online.store.repository.OrderRepository;
import com.com.online.store.online.store.repository.PaymentRepository;
import com.com.online.store.online.store.repository.ProductRepository;
import com.com.online.store.online.store.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}

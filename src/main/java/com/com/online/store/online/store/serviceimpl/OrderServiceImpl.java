package com.com.online.store.online.store.serviceimpl;

import com.com.online.store.online.store.model.Order;
import com.com.online.store.online.store.repository.OrderRepository;
import com.com.online.store.online.store.repository.ProductRepository;
import com.com.online.store.online.store.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order saveOrderMessageQueue(Order order) {
        return orderRepository.save(order);
    }
}

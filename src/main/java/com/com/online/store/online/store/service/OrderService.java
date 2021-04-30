package com.com.online.store.online.store.service;

import com.com.online.store.online.store.model.Order;

public interface OrderService {
    Order saveOrder(Order order);
    Order saveOrderMessageQueue(Order order);
}

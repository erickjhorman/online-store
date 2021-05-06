package com.com.online.store.online.store.service;

import com.com.online.store.online.store.model.Order;

public interface IOrderService {
    Order saveOrder(Order order);
    Order saveOrderMessageQueue(Order order);
}

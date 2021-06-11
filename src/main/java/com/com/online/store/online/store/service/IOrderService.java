package com.com.online.store.online.store.service;

import com.com.online.store.online.store.model.Order;

import java.util.concurrent.CompletableFuture;

public interface IOrderService {
    CompletableFuture<Order> saveOrder(Order order);
    Order saveOrderMessageQueue(Order order);
}

package com.com.online.store.online.store.service;

import com.com.online.store.online.store.model.PaypalPlatform;

public interface IPaypalService {
    void createPayment(PaypalPlatform paypalPlatform);
}

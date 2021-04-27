package com.com.online.store.online.store.service;

import com.com.online.store.online.store.model.StripePlatform;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.util.Optional;

public interface StripeService {
    Optional<PaymentIntent> createPayment(StripePlatform stripePlatform);
    PaymentIntent confirmPayment(String paymentId) throws StripeException;
    PaymentIntent cancelPayment(String paymentId) throws StripeException;
}

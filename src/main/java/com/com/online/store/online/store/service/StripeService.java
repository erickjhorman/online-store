package com.com.online.store.online.store.service;

import com.com.online.store.online.store.dto.CancelStripePaymentDto;
import com.com.online.store.online.store.exception.ResourceNotFoundException;
import com.com.online.store.online.store.model.StripePlatform;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.util.Optional;

public interface StripeService {
    Optional<PaymentIntent> createPayment(StripePlatform stripePlatform);
    Optional<PaymentIntent> confirmPayment(String paymentId) throws StripeException, ResourceNotFoundException;
    Optional<PaymentIntent> cancelPayment(String paymentId, CancelStripePaymentDto cancelStripePaymentDto) throws StripeException, ResourceNotFoundException;
}

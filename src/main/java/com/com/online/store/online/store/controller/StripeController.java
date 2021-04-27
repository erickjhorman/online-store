package com.com.online.store.online.store.controller;


import com.com.online.store.online.store.dto.OrderDto;
import com.com.online.store.online.store.service.PaypalService;
import com.com.online.store.online.store.service.StripeService;
import com.com.online.store.online.store.util.Constants;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@RequestMapping(StripeController.URL_BASE)
@Slf4j
@RequiredArgsConstructor
@Controller
public class StripeController {
    public static final String URL_BASE = "/api/v1/payments/id";
    private final StripeService stripeService;
    private final PaypalService paypalService;

    protected Optional<PaymentIntent> createPayment(OrderDto orderDto) {
        return stripeService.createPayment(orderDto.getPayment().stripeToEntity());
    }
    protected PaymentIntent confirmPayment(String paymentId) throws StripeException {
       return stripeService.confirmPayment(paymentId);
    }
    protected PaymentIntent cancelPayment(String paymentId) throws StripeException {
        return stripeService.cancelPayment(paymentId);
    }
}

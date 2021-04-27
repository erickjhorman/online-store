package com.com.online.store.online.store.controller;

import com.com.online.store.online.store.dto.*;
import com.com.online.store.online.store.model.Order;
import com.com.online.store.online.store.model.StripePlatform;
import com.com.online.store.online.store.service.OrderService;
import com.com.online.store.online.store.service.PaypalService;
import com.com.online.store.online.store.service.StripeService;
import com.com.online.store.online.store.util.Constants;
import com.com.online.store.online.store.util.GlobalValidations;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

import static  java.util.Optional.*;

@RestController
@RequestMapping(OrderController.URL_BASE)
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    public static final String URL_BASE = "/api/v1/orders";
    private final OrderService orderService;
    private final StripeController stripeController;

    @PostMapping
    private void saveOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result) throws StripeException {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, GlobalValidations.formatMessage(result));
        log.info(orderDto.toEntity().toString());
        Order orderSaved = orderService.saveOrder(orderDto.toEntity());
        Long orderId = orderSaved.getIdOrder();
        validatePaymentPlatform(orderDto,orderDto.getPayment().getPaymentPlatform());
    }

    private void validatePaymentPlatform(OrderDto orderDto,String paymentPlatform) throws StripeException {
        if (paymentPlatform.equals(Constants.STRIPE_PLATFORM)) {
           Optional<PaymentIntent> paymentIntent =  stripeController.createPayment(orderDto);
           if(paymentIntent.isEmpty()){
               empty();
           } else {
               stripeController.confirmPayment(paymentIntent.get().getId());
           }
        } else if (paymentPlatform.equals(Constants.PAYPAL_PLATFORM)) {
            //It's missing to create paypal logic
        }
    }
}

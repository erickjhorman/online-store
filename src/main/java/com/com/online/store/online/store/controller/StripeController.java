package com.com.online.store.online.store.controller;


import com.com.online.store.online.store.dto.CancelStripePaymentDto;
import com.com.online.store.online.store.dto.PaymentDto;
import com.com.online.store.online.store.exception.ResourceBadRequestException;
import com.com.online.store.online.store.exception.ResourceNotFoundException;
import com.com.online.store.online.store.service.IStripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping(StripeController.URL_BASE)
@Slf4j
@RequiredArgsConstructor
@RestController
public class StripeController {
    public static final String URL_BASE = "/api/v1/payment_intents";
    private final IStripeService stripeService;


    @PostMapping
    public ResponseEntity<String> createPayment(@Valid @RequestBody  PaymentDto paymentDto) throws ResourceBadRequestException {
        Optional<PaymentIntent> paymentResponseOpt =  stripeService.createPayment(paymentDto.stripeToEntity());
        if(paymentResponseOpt.isEmpty()){
            throw new ResourceBadRequestException("Stripe does not create  intent object");
        }
        return new ResponseEntity<>(paymentResponseOpt.get().toJson(),HttpStatus.OK);
    }

    @PostMapping("/{paymentId}/confirm")
    public ResponseEntity<String> confirmPayment(@PathVariable("paymentId") String paymentId) throws StripeException, ResourceBadRequestException, ResourceNotFoundException {
        Optional<PaymentIntent> paymentResponse = stripeService.confirmPayment(paymentId);
        if(paymentResponse.isEmpty()){
            throw new ResourceBadRequestException("Stripe does not confirm payment ");
        }
        String paymentStr = paymentResponse.get().toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<String> cancelPayment(@PathVariable("paymentId") String paymentId,@RequestBody CancelStripePaymentDto cancelStripeDto) throws StripeException, ResourceBadRequestException, ResourceNotFoundException {
        Optional<PaymentIntent> paymentResponse = stripeService.cancelPayment(paymentId,cancelStripeDto);
        if(paymentResponse.isEmpty()){
            throw new ResourceBadRequestException("Stripe does not cancel payment ");
        }
        return new ResponseEntity<>(paymentResponse.get().toJson(), HttpStatus.OK);
    }
}

package com.com.online.store.online.store.serviceimpl;

import com.com.online.store.online.store.dto.CancelStripePaymentDto;
import com.com.online.store.online.store.exception.ResourceNotFoundException;
import com.com.online.store.online.store.model.StripePlatform;
import com.com.online.store.online.store.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;
import static java.util.Optional.*;
@Service
@Slf4j
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.key.secret}")
    String secretKey;

    @Override
    public Optional<PaymentIntent> createPayment(StripePlatform stripePlatform) {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = null;
        ArrayList<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", stripePlatform.getTotalOrder());
        params.put("currency", stripePlatform.getStripeCurrency().toString());
        params.put("description",stripePlatform.getDescription());
        params.put("payment_method_types",paymentMethodTypes);

        try {
            paymentIntent = PaymentIntent.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ofNullable(paymentIntent);
    }

    public Optional<PaymentIntent> confirmPayment(String idPayment) throws StripeException, ResourceNotFoundException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = null;
        Optional<PaymentIntent>  paymentRetrieved =  retrievePayment(idPayment);
        if(paymentRetrieved.isEmpty()){
            throw new ResourceNotFoundException("Stripe paymentIntent not found " + " with id " + idPayment);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");

        paymentIntent = paymentRetrieved.get().confirm(params);
        if(paymentIntent == null){
            return empty();
        }
        return of(paymentIntent);
    }
    public Optional<PaymentIntent> cancelPayment(String idPayment, CancelStripePaymentDto cancelStripePaymentDto) throws StripeException, ResourceNotFoundException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = null;

        Optional<PaymentIntent>  paymentRetrieve =  retrievePayment(idPayment);
        if(paymentRetrieve.isEmpty()){
            throw new ResourceNotFoundException("Stripe paymentIntent not found " + " with id " + idPayment);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("cancellation_reason", cancelStripePaymentDto.getCancellationReason());

        paymentIntent = paymentRetrieve.get().cancel(params);
        if(paymentIntent == null){
            return empty();
        }
        return of(paymentIntent);
    }

    public Optional<PaymentIntent>  retrievePayment(String idPayment){
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = null;
        try {
            paymentIntent = PaymentIntent.retrieve(idPayment);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        if(paymentIntent == null){
            return empty();
        }
        return of(paymentIntent);
    }

}

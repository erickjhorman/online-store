package com.com.online.store.online.store.serviceimpl;

import com.com.online.store.online.store.model.Payment;
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
        ArrayList paymentMethodTypes = new ArrayList<>();
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

    public PaymentIntent confirmPayment(String idPayment) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = null;
        paymentIntent = PaymentIntent.retrieve(idPayment);

        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        paymentIntent.confirm(params);

        return paymentIntent;
    }

    public PaymentIntent cancelPayment(String idPayment) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = null;

        paymentIntent = PaymentIntent.retrieve(idPayment);
        paymentIntent.cancel();
        return paymentIntent;
    }

}

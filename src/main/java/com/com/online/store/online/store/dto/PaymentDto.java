package com.com.online.store.online.store.dto;

import com.com.online.store.online.store.model.PaypalPlatform;
import com.com.online.store.online.store.model.StripePlatform;
import com.com.online.store.online.store.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public  class PaymentDto {
    @NotNull
    @Positive
    private Integer totalOrder;
    private String description;

    @NotNull
    private Currency currency;

    @NotNull
    private String paymentPlatform;
    private String secretKey;

    public StripePlatform stripeToEntity(){
      return  StripePlatform.builder()
                .totalOrder(getTotalOrder())
                .description(getDescription())
                .stripeCurrency(getCurrency())
              .build();
    }

    public PaypalPlatform paypalToEntity(){ return null;}
}

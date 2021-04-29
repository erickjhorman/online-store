package com.com.online.store.online.store.model;

import com.com.online.store.online.store.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor @Builder
public class StripePlatform implements Serializable {
    private static final long serialVersionUID = new SecureRandom().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stripe")
    private Long stripeId;

    @Column(nullable = false)
    private Integer totalOrder;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Currency stripeCurrency;

    @Column(nullable = false)
    private String paymentPlatform;

    @Column(nullable = false)
    private String secretKey;

    @Column(name = "created_at")
    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;
}

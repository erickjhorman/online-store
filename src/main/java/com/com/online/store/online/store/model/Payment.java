package com.com.online.store.online.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TDT_PAYMENT")
@Builder
public class Payment implements Serializable {

    private static final long serialVersionUID = new SecureRandom().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long paymentId;

    @Column(name = "payment_status")
    @Length(min = 1, max = 25, message = "The length of the payment status must be between 5 and 50 characters")
    private String paymentStatus;

    @Range(min = 1, max = 100, message = "the amount must be between 1 and 100")
    @Positive
    private Integer amount;

    @Column(name = "created_at")
    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @NotNull
    @JoinColumn(name = "order_id", referencedColumnName = "id_order",nullable = false)
    @OneToOne
    private Order orderId;

    @JoinColumn(name = "stripe_id", referencedColumnName = "id_stripe",nullable = false)
    @ManyToOne
    private StripePlatform stripeId;


    @JoinColumn(name = "paypal_id", referencedColumnName = "id_paypal",nullable = false)
    @ManyToOne
    private PaypalPlatform paypalId;
}

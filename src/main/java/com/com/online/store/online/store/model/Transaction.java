package com.com.online.store.online.store.model;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "TDT_TRANSACTION")
public class Transaction implements Serializable {

    private static final long serialVersionUID = new SecureRandom().nextLong() + 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long transactionId;

    @Column(name = "created_at")
    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @NotNull
    @JoinColumn(name = "payment_id", referencedColumnName = "id_payment",nullable = false)
    @OneToOne
    private Payment paymentId;

}

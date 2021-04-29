package com.com.online.store.online.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Data
@Table(name = "TDT_INVOICE")
public class Invoice implements Serializable {

    private static final long serialVersionUID = new SecureRandom().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private long idInvoice;


    @Column(name = "created_at")
    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;


    @Column(name = "updated_at")
    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @NotNull
    @JoinColumn(name = "user_id", referencedColumnName = "id_user",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User userId;

    @NotNull
    @JoinColumn(name = "order_id", referencedColumnName = "id_order")
    @ManyToOne(fetch = FetchType.EAGER)
    private Order orderId;


}

package com.com.online.store.online.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.security.SecureRandom;


@Entity
@Getter @Setter
@AllArgsConstructor @Builder
public class PaypalPlatform implements Serializable {

    private static final long serialVersionUID = new SecureRandom().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paypal")
    private Long paypalId;


}

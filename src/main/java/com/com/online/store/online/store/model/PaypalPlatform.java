package com.com.online.store.online.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Random;

@Entity
@Getter @Setter
@AllArgsConstructor @Builder
public class PaypalPlatform {

    private static final long serialVersionUID = new Random().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paypal")
    private Long paypalId;


}

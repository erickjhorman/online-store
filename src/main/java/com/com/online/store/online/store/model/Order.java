package com.com.online.store.online.store.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TDT_ORDER")
@Builder
public class Order implements Serializable {

    private static final long serialVersionUID = new SecureRandom().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private long idOrder;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    //This relation is to map a list of products to a product entity
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<Product> productList;

    @NotNull
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;
}

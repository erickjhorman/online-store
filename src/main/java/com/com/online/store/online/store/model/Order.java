package com.com.online.store.online.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TDT_ORDER")
@Builder
public class Order implements Serializable {

    private static final long serialVersionUID = new Random().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private long idOrder;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    /*
    @NotNull
    @JoinColumn(name = "product_id", referencedColumnName = "id_product", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product productId;
    */

    //This relation is to map a list of products to a product entity
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<Product> productList;

    @NotNull
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;
}

package com.com.online.store.online.store.model;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TDT_PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = new SecureRandom().nextLong() + 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private long idProduct;

    @NotEmpty
    @Column(name = "product_name", nullable = false)
    @Length(min = 5, max = 25, message = "The length of the password must be between 5 and 25 characters")
    private String productName;

    @NotNull
    @Column(nullable = false)
    @Length(min = 5, max = 75, message = "The length of the password must be between 5 and 75 characters")
    private String description;

    @NotNull
    @Column(nullable = false)
    @Range(min = 1, max = 100, message = "the amount must be between 1 and 100")
    @Positive
    private int quantity;

    @NotNull
    @Column(nullable = false)
    @Range(min = 1, max = 1000000)
    @Positive
    private Integer price;

    @Column(nullable = false)
    @Range(min = 1, max = 1000)
    @Positive
    private int stock;

    @Column(nullable = false)
    @Size(min = 1, max = 15,message ="The length of the status must be between 1 and 15 characters")
    private String status;

    @Column(nullable = false)
    private String imageUrl;


    @Column(name = "created_at")
    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt = LocalDateTime.now();


    @Column(name = "updated_at")
    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @NotNull
    @JoinColumn(name = "sub_product_id", referencedColumnName = "id_sub_product", nullable = false)
    @ManyToOne
    private SubProduct subProductId;

    @ManyToOne
    private Order order;
}

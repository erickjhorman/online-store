package com.com.online.store.online.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TDT_SUB_PRODUCT")
@Builder
public class SubProduct implements Serializable {

    private static final long serialVersionUID = new Random().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sub_product")
    private long idSubProduct;

    @NotEmpty
    @Column(name = "sub_product_name",nullable = false)
    @Length(min = 1, max = 25, message = "The length of the password must be between 5 and 75 characters")
    private String subProductName;


    @Column(name = "created_at")
    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @ManyToOne
    private Product product;
}

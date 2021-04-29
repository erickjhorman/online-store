package com.com.online.store.online.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TDT_CITY")
public class City implements Serializable {

    private static final long serialVersionUID = new SecureRandom().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private long idCity;

    @NotEmpty
    @Column(name = "name_city",nullable = false)
    @Length(min = 1, max = 25, message = "The length of the password must be between 5 and 25 characters")
    private String nameCity;


    @Column(name = "created_at")
    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @Valid
    @NotNull
    @JoinColumn(name = "country_id", referencedColumnName = "id_country",nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country countryId;
}

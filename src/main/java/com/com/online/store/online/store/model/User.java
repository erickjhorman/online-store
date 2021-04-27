package com.com.online.store.online.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.util.Optional.*;

import java.util.Optional;
import java.util.Random;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TDT_USER")
public class User implements Serializable {

    private static final long serialVersionUID = new Random().nextLong() + 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long userId;

    private String name;

    private String email;

    private String password;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Past(message = "{user.created_at.past}")
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Past(message = "{user.updated_at.past}")
    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @JoinColumn(name = "country_id", referencedColumnName = "id_country", nullable = false)
    @ManyToOne
    private Country country;

    @JoinColumn(name = "city_id", referencedColumnName = "id_city", nullable = false)
    @NotNull
    @ManyToOne
    private City city;

    private boolean isHisBirthDay() {
        return true;
    }
}

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
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TDT_COUNTRY")
public class Country implements Serializable {

    private static final long serialVersionUID = new Random().nextLong() + 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    private long idCountry;

    @NotEmpty
    @Length(min = 1, max = 25, message = "The length of the password must be between 1 and 25 characters")
    @Column(nullable = false)
    private String nameCountry;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

}

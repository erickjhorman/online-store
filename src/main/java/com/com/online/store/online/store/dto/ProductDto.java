package com.com.online.store.online.store.dto;

import com.com.online.store.online.store.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDateTime;


@Getter
@Setter
public class ProductDto {

    @NotEmpty
    @Length(min = 5, max = 25, message = "The length of the password must be between 5 and 25 characters")
    private String productName;

    @NotNull
    @Length(min = 5, max = 75, message = "The length of the password must be between 5 and 75 characters")
    private String description;

    @NotNull
    @Range(min = 1, max = 100, message = "the amount must be between 1 and 100")
    @Positive
    private int quantity;

    @NotNull
    @Range(min = 1, max = 1000000)
    @Positive
    private Integer price;

    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;

    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @NotNull
    private SubProductDto subProductId;

    @NotNull
    private User userId;
}

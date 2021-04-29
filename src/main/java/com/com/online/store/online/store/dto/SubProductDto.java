package com.com.online.store.online.store.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Getter @Setter
public class SubProductDto {

    private long idSubProduct;

    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;

    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;
}

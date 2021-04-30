package com.com.online.store.online.store.messagebroker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class OrderDtoResponse {

    private String createdAt;
    private String updateAt;

    public OrderDtoResponse(String createdAt, String updateAt) {
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
}

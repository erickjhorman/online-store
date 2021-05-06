package com.com.online.store.online.store.messagebroker.dto;

import com.com.online.store.online.store.model.User;
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
    private String userMail;

    public OrderDtoResponse(String userMail,String createdAt, String updateAt) {
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.userMail = userMail;
    }
}

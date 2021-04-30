package com.com.online.store.online.store.messagebroker.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderStatus {
    private OrderDtoResponse order;
    private String status;  //Progress, Completed
    private String message;
}

package com.com.online.store.online.store.messagebroker.consumer;

import com.com.online.store.online.store.messagebroker.dto.OrderStatus;
import com.com.online.store.online.store.util.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {
    @RabbitListener(queues = Constants.ORDER_QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue : " + orderStatus);
    }
}

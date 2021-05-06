package com.com.online.store.online.store.messagebroker.consumer;

import com.com.online.store.online.store.exception.ResourceMailException;
import com.com.online.store.online.store.messagebroker.dto.OrderStatus;
import com.com.online.store.online.store.serviceimpl.MailServiceImpl;
import com.com.online.store.online.store.util.Constants;
import com.com.online.store.online.store.util.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.stereotype.Component;

import static com.com.online.store.online.store.util.Constants.ORDER_CONSUMER_ID;


@Component
@AllArgsConstructor
public class User {

    private final MailServiceImpl mailService;
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    @RabbitListener(queues = Constants.ORDER_QUEUE, id = ORDER_CONSUMER_ID)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue : " + orderStatus);
        if (orderStatus.getOrder().getUserMail() == null || orderStatus.getMessage() == null) {
            rabbitListenerEndpointRegistry.getListenerContainer(ORDER_CONSUMER_ID).stop();
            throw new ResourceMailException("Email not found or Message");
        } else {
            mailService.sendMailTrapMail(new NotificationEmail("Thanks for your order", orderStatus.getOrder().getUserMail(), orderStatus.getMessage()));
        }
    }
}

package com.com.online.store.online.store.messagebroker.publisher;

import com.com.online.store.online.store.controller.OrderController;
import com.com.online.store.online.store.messagebroker.dto.OrderDtoResponse;
import com.com.online.store.online.store.messagebroker.dto.OrderMessageQueueDto;
import com.com.online.store.online.store.dto.OrderResponseDto;
import com.com.online.store.online.store.messagebroker.dto.OrderStatus;
import com.com.online.store.online.store.model.Order;
import com.com.online.store.online.store.service.OrderService;
import com.com.online.store.online.store.util.Constants;
import com.com.online.store.online.store.util.GlobalValidations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(OrderController.URL_BASE)
@RequiredArgsConstructor
@Slf4j
public class OrderPublisher {
    public static final String URL_BASE = "/api/v1/orders";
    private final RabbitTemplate rabbitTemplate;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> saveOrderMessageQueue(@Valid @RequestBody OrderMessageQueueDto orderDto, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, GlobalValidations.formatMessage(result));
        log.info(orderDto.toEntity().toString());
        Order orderSaved = orderService.saveOrderMessageQueue(orderDto.toEntity());
        Long orderId = orderSaved.getIdOrder();
        OrderStatus orderStatus = new OrderStatus(new OrderDtoResponse("04-30-2024","04-30-2021"), "PROCESS", "order placed successfully " + orderId);
        log.info("Here");
        rabbitTemplate.convertAndSend(Constants.ORDER_EXCHANGE, Constants.ORDER_ROUTING_KEY, orderStatus);
        return ResponseEntity.ok(new OrderResponseDto(orderId));
    }


}

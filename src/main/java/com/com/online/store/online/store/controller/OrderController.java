package com.com.online.store.online.store.controller;

import com.com.online.store.online.store.dto.*;
import com.com.online.store.online.store.model.Order;
import com.com.online.store.online.store.service.IOrderService;
import com.com.online.store.online.store.util.GlobalValidations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RequestMapping(OrderController.URL_BASE)
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    public static final String URL_BASE = "/api/v1/orders";
    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> saveOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, GlobalValidations.formatMessage(result));
        log.info(orderDto.toEntity().toString());
        Order orderSaved = orderService.saveOrder(orderDto.toEntity());
        Long orderId = orderSaved.getIdOrder();
        return ResponseEntity.ok(new OrderResponseDto(orderId));
    }
}

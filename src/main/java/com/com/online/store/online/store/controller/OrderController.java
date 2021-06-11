package com.com.online.store.online.store.controller;

import com.com.online.store.online.store.dto.OrderDto;
import com.com.online.store.online.store.dto.OrderResponseDto;
import com.com.online.store.online.store.model.Order;
import com.com.online.store.online.store.service.IOrderService;
import com.com.online.store.online.store.util.GlobalValidations;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RequestMapping(OrderController.URL_BASE)
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")

public class OrderController {
    public static final String URL_BASE = "/api/v1/orders";
    private final IOrderService orderService;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<OrderResponseDto> saveOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result){
        if (result.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, GlobalValidations.formatMessage(result));
        CompletableFuture<Order> orderSaved = orderService.saveOrder(orderDto.toEntity());
        Long orderId = orderSaved.get().getIdOrder();
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponseDto(orderId));
    }
}

package com.com.online.store.online.store.controller;

import com.com.online.store.online.store.dto.UserDto;
import com.com.online.store.online.store.service.UserService;
import com.com.online.store.online.store.util.GlobalValidations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(UserController.URL_BASE)
@Slf4j
@RequiredArgsConstructor
public class UserController {
    public static final String URL_BASE = "/api/v1/users";

    private final UserService userService;

    @PostMapping
    private void saveUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
       if(result.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, GlobalValidations.formatMessage(result));
       userService.saveUser(userDto.toEntity());
    }
}

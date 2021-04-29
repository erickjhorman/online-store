package com.com.online.store.online.store.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ProductController.URL_BASE)
@NoArgsConstructor
public class ProductController {
    public static final String URL_BASE = "/api/v1/products";
}

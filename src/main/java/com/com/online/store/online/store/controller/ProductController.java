package com.com.online.store.online.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProductController.URL_BASE)
public class ProductController {

    public static final String URL_BASE = "/api/v1/products";



}

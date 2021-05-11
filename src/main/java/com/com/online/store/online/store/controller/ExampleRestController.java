package com.com.online.store.online.store.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExampleRestController {
    @GetMapping("/hello")
    String helloUser(@AuthenticationPrincipal OidcUser user) {
        return "Hello " + user.getAttributes().toString();
    }

    @GetMapping("/api/private")
    String privateRoute(@AuthenticationPrincipal OidcUser user) {
        return "you're here";
    }

}
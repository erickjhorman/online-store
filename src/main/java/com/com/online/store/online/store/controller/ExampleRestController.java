package com.com.online.store.online.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
class ExampleRestController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('Admin')")
    String helloUser(@AuthenticationPrincipal Principal user) {
        System.out.println("I added for testing");

        return "Hello " + user;
    }

    @PreAuthorize("hasAuthority('users')")
    @GetMapping("/api/users")
    String users(@AuthenticationPrincipal Jwt jwt) {
        return "you're here" + jwt.getClaims().toString();
    }

    @PreAuthorize("hasAuthority('users')")
    @GetMapping("/api/private")
    String privateRoute(@AuthenticationPrincipal Principal user) {
        return "you're here";
    }

    private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authentication) {
        return this.authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(), authentication.getName());
    }
}
package com.com.online.store.online.store.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResourceBadRequestException extends Exception {
    public ResourceBadRequestException(String message) {
        super(message);
    }
}

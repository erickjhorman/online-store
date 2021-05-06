package com.com.online.store.online.store.exception;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;

@AllArgsConstructor
public class ResourceMailException extends RuntimeException{
    public ResourceMailException(String message) {
        super(message);
    }
}

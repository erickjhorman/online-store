package com.com.online.store.online.store.exception;

import lombok.SneakyThrows;

public class SneakyThrowsException implements Runnable {

    @Override
    @SneakyThrows
    public void run() {
        try {
            throw new InterruptedException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

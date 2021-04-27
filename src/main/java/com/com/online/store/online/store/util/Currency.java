package com.com.online.store.online.store.util;

public enum Currency {
    USD,
    EUR,
    COP;

    @Override
    public String toString() {
        return name().strip().toLowerCase();
    }
}

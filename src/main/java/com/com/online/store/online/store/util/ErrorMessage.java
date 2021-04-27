package com.com.online.store.online.store.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter  @AllArgsConstructor @Builder
public class ErrorMessage {
    private String code;
    private List<Map<String,String>> messages;
}

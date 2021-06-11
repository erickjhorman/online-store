package com.com.online.store.online.store.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public String mailTrapEmail(String message,String imageResourceName) {
        final Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("imageResourceName",imageResourceName); // so that we can reference it from HTML
        return templateEngine.process("mailTemplate", context);
    }
}

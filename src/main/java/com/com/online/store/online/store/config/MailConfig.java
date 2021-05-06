package com.com.online.store.online.store.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@AllArgsConstructor
public class MailConfig {

    @Autowired
    private final Environment env;

    @Bean
    public JavaMailSender mailTrapMailSender() {

        String host = env.getProperty("mailtrap.host");
        Integer port = Integer.parseInt(env.getProperty("mailtrap.port"));
        String username = env.getProperty("mailtrap.username");
        String password = env.getProperty("mailtrap.password");
        String protocol = env.getProperty("mailtrap.protocol");

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setProtocol(protocol);
        return javaMailSender;
    }


}

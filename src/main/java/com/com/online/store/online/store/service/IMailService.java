package com.com.online.store.online.store.service;

import com.com.online.store.online.store.exception.ResourceMailException;
import com.com.online.store.online.store.util.NotificationEmail;

public interface IMailService {
    void sendMailTrapMail(NotificationEmail notificationEmail) throws ResourceMailException;
}

package com.cwa.springboot_app.ObserverStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class NotificationStrategyFactory {

    private final JavaMailSender mailSender;

    @Autowired
    public NotificationStrategyFactory(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public NotificationStrategy getStrategy() {
            return new NotifierParEmail(mailSender);
      
    }
}

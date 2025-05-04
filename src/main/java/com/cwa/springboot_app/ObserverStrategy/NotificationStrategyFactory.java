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

    public NotificationStrategy getStrategy(String strategynotif) {
         if(strategynotif.equals("email")){
            return new NotifierParEmail(mailSender);
         }else if(strategynotif.equals("SMS")){
            return new NotifierParSMS();
         }else{
            return new NotifierParEmail(mailSender);
         }
      
    }
}

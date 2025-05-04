package com.cwa.springboot_app.ObserverStrategy;

import com.cwa.springboot_app.dto.EtudiantDto;

public class NotifierParSMS implements NotificationStrategy {

    @Override
    public void notifier(String msg, EtudiantDto membre) {
        System.out.println(msg);
    }

}

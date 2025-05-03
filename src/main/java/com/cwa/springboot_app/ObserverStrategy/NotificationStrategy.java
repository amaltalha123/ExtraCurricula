package com.cwa.springboot_app.ObserverStrategy;

import com.cwa.springboot_app.dto.EtudiantDto;

public interface NotificationStrategy {
    void notifier(String msg,EtudiantDto membre);
}

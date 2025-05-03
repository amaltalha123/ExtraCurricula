package com.cwa.springboot_app.ObserverStrategy;

import com.cwa.springboot_app.dto.EtudiantDto;

public class MembreBureauObserver implements Observer {

    private final EtudiantDto membre;
    private final NotificationStrategy strategy;

    public MembreBureauObserver(EtudiantDto membre, NotificationStrategy strategy) {
        this.membre = membre;
        this.strategy = strategy;
    }

    @Override
    public void update(String msg) {
        strategy.notifier("Bonjour " + membre.getFullname() + ", " + msg,membre);
    }
}
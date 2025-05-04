package com.cwa.springboot_app.ObserverStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.cwa.springboot_app.dto.EtudiantDto;
import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.service.ClubService;


@Service
public class NotificationService implements Subject{
    private final NotificationStrategyFactory strategyFactory;
    private final ClubService clubService;


    public NotificationService(ClubService clubService,NotificationStrategyFactory strategyFactory) {
        this.clubService = clubService;
        this.strategyFactory=strategyFactory;
    }
    
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifierObservers(String msg) {
        for (Observer o : observers) {
            o.update(msg);
        }
    }

    public boolean notifierMembresDuBureau(Club club, String msg, String strategynotif) {
    List<EtudiantDto> membreBureau = clubService.getClubMembreBureau(club);
    
    if (membreBureau == null || membreBureau.isEmpty()) {
        return false; 
    }
   
    NotificationStrategy strategy = strategyFactory.getStrategy(strategynotif);
    List<CompletableFuture<Void>> futures = new ArrayList<>();
    
    for (EtudiantDto membre : membreBureau) {
        Observer obs = new MembreBureauObserver(membre, strategy);
        addObserver(obs);
        
        // Envoyer l'e-mail de manière asynchrone
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            obs.update(msg);
        });
        futures.add(future);
    }

    // Attendre que tous les e-mails soient envoyés
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    
    observers.clear();
    
    return true; 
}
}

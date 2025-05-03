package com.cwa.springboot_app.ObserverStrategy;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.cwa.springboot_app.dto.EtudiantDto;

public class NotifierParEmail implements NotificationStrategy {

    private final JavaMailSender mailSender;

    public NotifierParEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void notifier(String msg,EtudiantDto membre) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(membre.getUsername()); // à remplacer par l’adresse réelle du destinataire
        message.setSubject("ENSATe clubs");
        message.setText(msg);
        message.setFrom("talhaamal204@gmail.com");

        mailSender.send(message);
        System.out.println("Email envoyé avec succès !");
    }
}
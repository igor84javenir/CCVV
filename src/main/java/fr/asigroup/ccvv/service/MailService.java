package fr.asigroup.ccvv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService{

    @Autowired
    JavaMailSender javaMailSender;

    public void envoiEmail(String destinataire, String sujet, String texte) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("samia.zaboub@javenir84.com");  // Expéditeur
        simpleMailMessage.setTo(destinataire);  // Destinataire
        simpleMailMessage.setSubject(sujet);
        simpleMailMessage.setText(texte);

        this.javaMailSender.send(simpleMailMessage);
    }
}

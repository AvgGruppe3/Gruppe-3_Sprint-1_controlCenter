package com.acme.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@PropertySource("classpath:email.properties")
@Service
public class EmailService {
    @Value("${email.sender}")
    private String sender;
    @Value("${email.recipient}")
    private String recipient;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(int temperature, String alertStage) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(sender);
        msg.setTo(recipient);
        msg.setSubject("Industrieanlage " + alertStage);
        msg.setText("Die Industrieanlage hat eine Temperature von: " + temperature + "°C");

        javaMailSender.send(msg);
    }
}

package com.acme.email;

import com.acme.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@PropertySource("classpath:email.properties")
@Service
public class EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Value("${email.sender}")
    private String sender;
    @Value("${email.recipient}")
    private String recipient;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Sensor sensor, String alertStage) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(sender);
        msg.setTo(recipient);
        msg.setSubject(sensor.machine + " " + alertStage);
        msg.setText("Die " + sensor.machine + " hat eine Temperature von: " + sensor.temperature + "°C");
        logger.info("send email to " + recipient);
        try {
            javaMailSender.send(msg);
            logger.info("successfully sent an e-mail");
        }catch (MailException e){
            logger.info("Failed to send an e-mail; " +e.getMessage());
        }
    }
}

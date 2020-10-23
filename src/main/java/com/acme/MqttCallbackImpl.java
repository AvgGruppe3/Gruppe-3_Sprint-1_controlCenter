package com.acme;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MqttCallbackImpl implements MqttCallback {
    private final EmailService emailService;
    private final Temperature temperature;

    @Autowired
    public MqttCallbackImpl(EmailService emailService, Temperature temperature) {
        this.emailService = emailService;
        this.temperature = temperature;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        //Auto-generated method stub
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        String temperatureString = new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);
        System.out.println(topic + ": " + temperatureString);
        int temperatureValue = Integer.parseInt(temperatureString);
        temperature.setValue(temperatureValue);
        emailService.sendEmail(temperatureValue);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //Auto-generated method stub
    }
}

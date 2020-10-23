package com.acme.mqtt;

import com.acme.EmailService;
import com.acme.Topic;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MqttCallbackImpl implements MqttCallback {
    private final EmailService emailService;

    @Autowired
    public MqttCallbackImpl(EmailService emailService) {
        this.emailService = emailService;
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
        if(Topic.Temperature_1.value.equals(topic)){
            Topic.Temperature_1.temperature = temperatureValue;
        }else if(Topic.Temperature_2.value.equals(topic)){
            Topic.Temperature_2.temperature = temperatureValue;
        }
        
        if(temperatureValue >35) {
            emailService.sendEmail(temperatureValue, "Alarm");
        }else if(temperatureValue >25){
            emailService.sendEmail(temperatureValue, "Warnung");
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //Auto-generated method stub
    }
}

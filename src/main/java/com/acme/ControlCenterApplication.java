package com.acme;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ControlCenterApplication {

    public static void main(String[] args) throws MqttException {
        ConfigurableApplicationContext context = SpringApplication.run(ControlCenterApplication.class, args);
        context.getBean(SubscribeMqtt.class).subscribe();
    }
}

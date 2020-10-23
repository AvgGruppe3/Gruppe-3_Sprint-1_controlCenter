package com.acme;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SubscribeMqtt {
    private final MqttCallbackImpl mqttCallbackImpl;

    public SubscribeMqtt(MqttCallbackImpl mqttCallbackImpl) {
        this.mqttCallbackImpl = mqttCallbackImpl;
    }

    public void subscribe() throws MqttException {
        MqttAsyncClient myClient = new MqttAsyncClient("tcp://192.168.0.171:1883", UUID.randomUUID().toString());

        myClient.setCallback(mqttCallbackImpl);

        IMqttToken token = myClient.connect();
        token.waitForCompletion();
        String[] topics = {"hska/avg/temperature", "hska/avg/altitude", "hska/avg/press"};
        int[] qoss = {0, 0, 0};
        myClient.subscribe(topics, qoss);
    }
}

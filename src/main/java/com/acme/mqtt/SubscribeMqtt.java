package com.acme.mqtt;

import com.acme.Topic;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@PropertySource("classpath:mqtt.properties")
@Component
public class SubscribeMqtt {

    @Value("${mqtt.broker.uri}")
    private String uri;

    private final MqttCallbackImpl mqttCallbackImpl;

    public SubscribeMqtt(MqttCallbackImpl mqttCallbackImpl) {
        this.mqttCallbackImpl = mqttCallbackImpl;
    }

    public void subscribe() throws MqttException {
        MqttAsyncClient myClient = new MqttAsyncClient(uri, UUID.randomUUID().toString());

        myClient.setCallback(mqttCallbackImpl);

        IMqttToken token = myClient.connect();
        token.waitForCompletion();
        String[] topics = {Topic.Temperature_1.path, Topic.Temperature_2.path};
        int[] qoss = {0, 0};
        myClient.subscribe(topics, qoss);
    }
}

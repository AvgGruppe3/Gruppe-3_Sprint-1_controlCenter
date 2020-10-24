package com.acme;

public enum Sensor {
    SENSOR_1("hska/avg/temperature1", "Industrieanlage 1"),
    SENSOR_2("hska/avg/temperature2", "Industrieanlage 2");

    public final String mqttTopic;
    public int temperature;
    public final String machine;

     Sensor(String mqttTopic, String machine) {
        this.mqttTopic = mqttTopic;
        this.temperature = 0;
        this.machine = machine;
    }
    String getMqttTopic(){
         return  mqttTopic;
    }

    public static Sensor getSensorByMqttTopic(String value) {
        for(Sensor sensor : values()) {
            if (sensor.getMqttTopic().equalsIgnoreCase(value)) {
                return sensor;
            }
        }
        throw new IllegalArgumentException();
    }
}

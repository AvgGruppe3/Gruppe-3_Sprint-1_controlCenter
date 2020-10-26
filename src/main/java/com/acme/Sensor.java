package com.acme;

public enum Sensor {
    SENSOR_1("hska/avg/temperature1", "Industrieanlage 1"),
    SENSOR_2("hska/avg/temperature2", "Industrieanlage 2");

    public final String mqttTopic;
    public double temperature;
    public final String machine;
    public long timestampEmail;

     Sensor(String mqttTopic, String machine) {
        this.mqttTopic = mqttTopic;
        this.temperature = 0.0;
        this.timestampEmail = 0L;
        this.machine = machine;
    }

    public static Sensor getSensorByMqttTopic(String value) {
        for(Sensor sensor : values()) {
            if (sensor.mqttTopic.equalsIgnoreCase(value)) {
                return sensor;
            }
        }
        throw new IllegalArgumentException();
    }
}

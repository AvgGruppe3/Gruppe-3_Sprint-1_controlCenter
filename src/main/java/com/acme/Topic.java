package com.acme;

public enum Topic {
    Temperature_1("hska/avg/temperature1"),
    Temperature_2("hska/avg/temperature2");

    public final String value;
    public int temperature;
     Topic(String value) {
        this.value = value;
        this.temperature = 0;
    }

}

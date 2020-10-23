package com.acme;

public enum Topic {
    Temperature_1("hska/avg/temperature1"),
    Temperature_2("hska/avg/temperature2");

    public final String path;
    public int temperature;
     Topic(String path) {
        this.path = path;
        this.temperature = 0;
    }

}

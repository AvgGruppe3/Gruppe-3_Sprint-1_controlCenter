package com.acme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final Temperature temperature;

    @Autowired
    public Controller(Temperature temperature) {
        this.temperature = temperature;
    }

    @GetMapping("/temp")
    public ResponseEntity<String> getTemperature(){
        return ResponseEntity.ok(String.valueOf(temperature.getValue()));
    }
}

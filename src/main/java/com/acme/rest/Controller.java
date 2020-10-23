package com.acme.rest;

import com.acme.Topic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/temp1")
    public ResponseEntity<String> getTemperature1(){
        return ResponseEntity.ok(String.valueOf(Topic.Temperature_1.temperature));
    }

    @GetMapping("/temp2")
    public ResponseEntity<String> getTemperature2(){
        return ResponseEntity.ok(String.valueOf(Topic.Temperature_2.temperature));
    }
}

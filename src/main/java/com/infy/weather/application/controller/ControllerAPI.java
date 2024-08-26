package com.infy.weather.application.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.infy.weather.application.service.WeatherAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/weather/api")
public class ControllerAPI {

    @Autowired
    WeatherAPIService service;

    /*
    Get the last 7 days weather history for a location
    @Param locationId should match one of the location details already exists in the database
    */
    @GetMapping("/history")
    public JsonNode getWeatherHistory(@RequestParam("locationId") int locationId){
        log.info("Have started the application");
        return null;
    }


    /*
    Get all locations in the database
    */
    @GetMapping("/locations")
    public JsonNode getWeatherHistory(){
        log.info("Have started the application");
        return null;
    }
}

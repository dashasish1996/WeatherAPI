package com.infy.weather.application.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.infy.weather.application.model.RequestModel;
import com.infy.weather.application.service.WeatherAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public JsonNode getWeatherHistory(@RequestBody RequestModel locationDetails){
        return service.getHistoricalData(locationDetails);
    }


    /*
    Get all locations in the database
    */
    @GetMapping("/locations/fetch")
    public JsonNode getExistingLocations(){
        return service.getExistingLocations();
    }

    /*
    Check if the provided query parameter is valid
    */
    @GetMapping("/locations/check")
    public JsonNode checkValidLocation(@RequestParam("queryParameter") String queryParameter){
        return service.checkValidLocation(queryParameter);
    }
}

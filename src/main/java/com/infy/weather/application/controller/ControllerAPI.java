package com.infy.weather.application.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.infy.weather.application.constants.AppConstants;
import com.infy.weather.application.model.RequestModel;
import com.infy.weather.application.service.WeatherAPIService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/weather/api")
public class ControllerAPI {

    // TODO Add Actuator

    @Autowired
    WeatherAPIService service;

    /*
    Get the last 7 days weather history for a location
    @Param locationId should match one of the location details already exists in the database
    */
    @GetMapping("/history")
    public JsonNode getWeatherHistory(@RequestBody RequestModel locationDetails,
                                      @RequestParam(defaultValue = "0", required = false)
                                      @Min(value = 0, message = "delay must be at least 0")
                                      @Max(value = 999, message = "delay must be less than 1000")
                                      int delay) throws InterruptedException {
        log.info("Delay Requested(seconds): {}", delay);
        Thread.sleep(delay * AppConstants.SECONDS_TO_MILLI_MULTIPLIER);
        return service.getHistoricalData(locationDetails);
    }


    /*
    Get all locations in the database
    */
    @GetMapping("/locations/fetch")
    public JsonNode getExistingLocations() {
        return service.getExistingLocations();
    }

    /*
    Check if the provided query parameter is valid
    */
    @GetMapping("/locations/check")
    public JsonNode checkValidLocation(@RequestParam("queryParameter") String queryParameter) {
        return service.checkValidLocation(queryParameter);
    }
}

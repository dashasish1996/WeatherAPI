package com.infy.weather.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.infy.weather.application.dao.LocationDetailsJPA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherAPIService {

    @Autowired
    LocationDetailsJPA locationDetailsJPA;

    public JsonNode getHistoricalData(int locationId){

    }

    public JsonNode getExistingLocations(){
        var availableLocations = locationDetailsJPA.findAll();
    }

    public JsonNode checkValidLocation(String queryParameter) {
    }
}

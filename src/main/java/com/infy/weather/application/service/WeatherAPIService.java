package com.infy.weather.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.weather.application.constants.AppConstants;
import com.infy.weather.application.dao.LocationDetailsJPA;
import com.infy.weather.application.model.RequestModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@Service
public class WeatherAPIService {
    @Value("${weather.api.base.url}")
    String apiBaseUrl;
    @Value("${weather.api.key}")
    String key;
    private final CloseableHttpClient httpClient;
    private final LocationDetailsJPA locationDetailsJPA;
    static final ObjectMapper mapper = new ObjectMapper();

    public WeatherAPIService(CloseableHttpClient httpClient, LocationDetailsJPA locationDetailsJPA) {
        this.httpClient = httpClient;
        this.locationDetailsJPA = locationDetailsJPA;
    }

    public JsonNode getHistoricalData(RequestModel locatioDetails) {
        try {
            URIBuilder uriBuilder = new URIBuilder(apiBaseUrl.concat(AppConstants.API_SUFFIX_HISTORY));
            uriBuilder.addParameter("key", key);
            uriBuilder.addParameter("q", locatioDetails.getQueryParameter());
            uriBuilder.addParameter("dt", locatioDetails.getDate());
            HttpGet request = new HttpGet(uriBuilder.build());
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                int statusCode = response.getCode();
                var jsonString = EntityUtils.toString(response.getEntity());
                var jsonResponse = mapper.readTree(jsonString);
                if (statusCode >= 200 && statusCode < 300) {
                    // Success - Return the JSON response
                    return jsonResponse;
                } else if (statusCode == 404) {
                    throw new RuntimeException("ERROR 404: For location: " + locatioDetails.getQueryParameter() + " [" + jsonResponse.get("error").get("message") + "]");
                } else if (statusCode == 500) {
                    throw new RuntimeException("ERROR 500: Internal server error from API.");
                } else {
                    throw new RuntimeException("ERROR " + statusCode + ": Unexpected response status [" + jsonResponse.get("error").get("message") + "]");
                }
            }
        } catch (IOException | ParseException | RuntimeException | URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public JsonNode getExistingLocations() {
        // TODO
        var availableLocations = locationDetailsJPA.findAll();
        return null;
    }

    public JsonNode checkValidLocation(String queryParameter) {
        // TODO
        return null;
    }
}

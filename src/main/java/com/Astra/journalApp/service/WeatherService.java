package com.Astra.journalApp.service;

import com.Astra.journalApp.api.response.WeatherResponse;
import com.Astra.journalApp.cache.AppCache;
import com.Astra.journalApp.constant.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private  String apiKey;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWhether(String city){

        WeatherResponse weatherResponse = redisService.get("Weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null){
            return weatherResponse;
        }else {
            String finalApi =appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY,city).replace(PlaceHolders.API_KEY,apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.POST, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null){
                redisService.set("Weather_of_"+ city,body,300l);
            }
            return body;
        }

        /*The process of converting JSON response into corresponding Java object is
        known as Deserialization.

        Json to POJO -->Deserialization
        POJO to JSON -->Serialization
        */

    }


}

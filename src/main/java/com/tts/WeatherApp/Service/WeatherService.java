package com.tts.WeatherApp.Service;

import com.tts.WeatherApp.Model.Response;
//import com.tts.WeatherApp.Repository.ZipCodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
//    @Autowired
//    private ZipCodesRepository zipCodesRepository;

    @Value("${api_key}")
    private String apiKey;

    public Response getForecast(String zipCode) {
        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" +
                zipCode + "&units=imperial&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate.getForObject(url, Response.class);
        } catch (
                HttpClientErrorException ex) {
            Response response = new Response();
            response.setName("error");
            response.add(zipCode);
            return response;
        }



    }
}
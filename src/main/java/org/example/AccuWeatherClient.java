package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AccuWeatherClient implements GetWeather, WeatherMapper {
    private static final String accuWeatherURL = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/";
    private static final String accuLocationURL = "http://dataservice.accuweather.com/locations/v1/cities/search";
    private static final String accuParams = "?apikey=%s&q=%s";
    private static final String accuKey = "6xg5j7hoh2AgyrSFNMhXOcqpDJTsQohq";


    public String getCityKey(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(json);
        return jsonNode.get("Key").asText();
    }

    public HttpResponse<String> getWeather(String city) throws IOException, InterruptedException {
        final String apiLocationUrl = String.format(accuLocationURL + accuParams, accuKey, city);
        HttpClient httpLocationClient = HttpClient.newHttpClient();
        HttpRequest httpLocationRequest = HttpRequest.newBuilder().uri(URI.create(apiLocationUrl)).build();
        HttpResponse<String> locationJson = httpLocationClient.send(httpLocationRequest, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(locationJson.body());
        String cityKey = jsonNode.get("Key").asText();

        final String apiUrl = String.format(accuWeatherURL + accuParams, accuKey,cityKey);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }


    public HttpResponse<String> getLocation(String city) throws IOException, InterruptedException {
        final String apiUrl = String.format(accuLocationURL + accuParams, accuKey, city);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public Weather weatherMapper(String json) {
        return null;
    }
}

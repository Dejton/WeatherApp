package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenWeatherClient implements GetWeather, WeatherMapper{

    private final String apiLocationUrl = "http://api.openweathermap.org/geo/1.0/direct";
    private final String apiWeatherUrl = "http://api.openweathermap.org/data/2.5/weather";
    private final String apiLocationParams = "?q=%s&limit=5&appid=%s";
    private final String apiWeatherParams = "?lat=%s&lon=%s&exclude=metric&appid=%s";
    private final String apiKey = "83cf955e9bb1d69314f418c3a8433efc";

    @Override
    public HttpResponse<String> getWeather(String city) throws IOException, InterruptedException {
        final String apiLocationURL = String.format(apiLocationUrl + apiLocationParams, city, apiKey);
        HttpClient httpLocationClient = HttpClient.newHttpClient();
        HttpRequest httpLocationRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiLocationURL))
                .build();
        HttpResponse<String> locationJson = httpLocationClient.send(httpLocationRequest, HttpResponse.BodyHandlers.ofString());


        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(locationJson.body());
        String lat = jsonNode.get(0).get("lat").asText();
        String lon = jsonNode.get(0).get("lon").asText();

        final String apiWeatherURL = String.format(apiWeatherUrl + apiWeatherParams, lat,lon,apiKey);
        HttpClient httpWeatherClient = HttpClient.newHttpClient();
        HttpRequest httpWeatherRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiWeatherURL))
                .build();
        return httpWeatherClient.send(httpWeatherRequest, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public Weather weatherMapper(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(json);
        return new Weather(
                jsonNode.get("name").asText(),
                jsonNode.get("sys").get("country").asText(),
                jsonNode.get("weather").get(0).get("description").asText(),
                (jsonNode.get("main").get("temp").asInt() - 273)
        );
    }
}

package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherStackClient implements GetWeather, WeatherMapper{

    private final String weatherStackURL = "http://api.weatherstack.com/current";
    private final String weatherStackParams = "?access_key=%s&query=%s";
    private final String weatherStackKey = "74fc690f1bc72dbb8e5b811dd4460c42";

    @Override
    public HttpResponse<String> getWeather(String city) throws IOException, InterruptedException {
        final String apiUrl = String.format(weatherStackURL + weatherStackParams, weatherStackKey, city);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public Weather weatherMapper(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(json);

        return new Weather(
          jsonNode.get("location").get("name").asText(),
          jsonNode.get("location").get("country").asText(),
          jsonNode.get("current").get("weather_descriptions").get(0).asText(),
          jsonNode.get("current").get("temperature").asInt()
        );
    }
}

package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherStackClient implements GetWeather{

    private final String weatherStackURL = "http://api.weatherstack.com/current";
    private final String weatherStackParams = "?access_key=%s&query=%s";
    private final String weatherStackKey = "9105820342ec521b685d0f4d48514f61";

    @Override
    public HttpResponse<String> getWeather(String city) throws IOException, InterruptedException {
        final String apiUrl = String.format(weatherStackURL + weatherStackParams, weatherStackKey, city);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}

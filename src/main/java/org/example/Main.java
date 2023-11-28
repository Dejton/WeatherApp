package org.example;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        WeatherStackClient weatherStackClient = new WeatherStackClient();
        AccuWeatherClient accuWeatherClient = new AccuWeatherClient();

        HttpResponse<String> katowice = accuWeatherClient.getWeather("Katowice");

        System.out.println(katowice.body());
    }
}

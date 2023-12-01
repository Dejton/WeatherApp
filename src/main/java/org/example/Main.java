package org.example;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, CsvValidationException {
        WeatherStackClient weatherStackClient = new WeatherStackClient();
        AccuWeatherClient accuWeatherClient = new AccuWeatherClient();
        OpenWeatherClient openWeatherClient = new OpenWeatherClient();


        // System.out.println(weatherStackClient.weatherMapper(weatherStackClient.getWeather("Katowice").body()));
        // System.out.println(openWeatherClient.weatherMapper(openWeatherClient.getWeather("Katowice").body()));
        // System.out.println(accuWeatherClient.weatherMapper(accuWeatherClient.getWeather("Katowice").body()));
        // SaveDataToDataBase.addDataToDataBase("Katowice");
        System.out.println(GetAverageData.getAverageTemperature(GetAverageData.getTemp()));

    }

}

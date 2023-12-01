package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SaveDataToDataBase {
    static Path path = Path.of("./DataBase.csv");
    private static final AccuWeatherClient accuWeatherClient = new AccuWeatherClient();
    private static final OpenWeatherClient openWeatherClient = new OpenWeatherClient();
    private static final WeatherStackClient weatherStackClient = new WeatherStackClient();




    public static void addDataToDataBase(String city) throws IOException, InterruptedException {
        final Weather accuWeatherData = accuWeatherClient.weatherMapper(accuWeatherClient.getWeather(city).body());
        final Weather openWeatherData = openWeatherClient.weatherMapper(openWeatherClient.getWeather(city).body());
        final Weather weatherStackData = weatherStackClient.weatherMapper(weatherStackClient.getWeather(city).body());

        Files.write(path, (accuWeatherData.toString() + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        Files.write(path, (openWeatherData.toString() + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        Files.write(path, (weatherStackData.toString() + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}

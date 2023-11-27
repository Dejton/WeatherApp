package org.example;

import java.util.Objects;

public class Weather {
    private final String city;
    private final String country;
    private final String cloudy;
    private final int temperature;

    public Weather(String city, String country, String cloudy, int temperature) {
        this.city = city;
        this.country = country;
        this.cloudy = cloudy;
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Weather weather = (Weather) o;
        return temperature == weather.temperature && Objects.equals(city, weather.city) && Objects.equals(country, weather.country) && Objects.equals(cloudy, weather.cloudy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, cloudy, temperature);
    }

    @Override
    public String toString() {
        return "Weather{" + "city='" + city + '\'' + ", country='" + country + '\'' + ", cloudy='" + cloudy + '\'' + ", temperature=" + temperature + '}';
    }
}

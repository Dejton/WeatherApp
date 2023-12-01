package org.example;

import java.util.Objects;
import java.util.UUID;

public class Weather {
    private UUID id;
    private final String city;
    private final String country;
    private final String cloudy;
    private final int temperature;

    public Weather(String city, String country, String cloudy, int temperature) {
        this.id = createUuid(country, city);
        this.city = city;
        this.country = country;
        this.cloudy = cloudy;
        this.temperature = temperature;
    }
    private UUID createUuid(String country, String city) {
        return UUID.nameUUIDFromBytes((country + city).getBytes());
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
        return String.format("ID: %s, City: %s, Country: %s, Cloudy: %s, Temperature: %d", id ,city,country,cloudy, temperature);
    }
}

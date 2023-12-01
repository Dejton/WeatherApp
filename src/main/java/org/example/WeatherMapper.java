package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherMapper {
    Weather weatherMapper(String json) throws JsonProcessingException;
}

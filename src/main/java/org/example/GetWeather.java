package org.example;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface GetWeather {
    HttpResponse<String> getWeather(String city) throws IOException, InterruptedException;
}

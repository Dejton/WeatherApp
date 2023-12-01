package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAverageData {

    public static List<Integer> getTemp() throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader("DataBase.csv"));
        List<Integer> temperatures = new ArrayList<>();

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            String temperatureColumn = nextLine[nextLine.length - 1];

            try {
                int temperature = Integer.parseInt(temperatureColumn.substring(temperatureColumn.length()-3).trim());
                temperatures.add(temperature);
            } catch (NumberFormatException e) {
                System.err.println("Nie prawidłowa temperatura: " + temperatureColumn);
            }
        }
        return temperatures;
    }

    public static int getAverageTemperature(List<Integer> input) {
        int sum = 0;
        for (int e : input) {
            sum += e;
        }
        return sum / input.size();
    }
}

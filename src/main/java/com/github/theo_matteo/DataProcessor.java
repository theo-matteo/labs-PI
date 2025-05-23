package com.github.theo_matteo;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class DataProcessor {

    public static ArrayList<Map<String, Double>> getDataSummary(int year, Genero genero) throws Exception {

        ArrayList<Map<String, Double>> array = new ArrayList<>();
        String headerExpectationYears = genero == Genero.MASCULINO
                ? "Life expectancy - Sex: male - Age: 0 - Variant: estimates"
                : "Life expectancy - Sex: female - Age: 0 - Variant: estimates";

        Reader in = new FileReader("data.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .get()
                .parse(in);

        for (CSVRecord record : records) {
            int yearCSV = Integer.parseInt(record.get("Year"));
            if (yearCSV == year) {
                Double expectation = Double.parseDouble(record.get(headerExpectationYears));
                String country = record.get("Entity");
                Map<String, Double> countryAndValue = new HashMap<>();
                countryAndValue.put(country, expectation);
                array.add(countryAndValue);
            }
        }

        array.sort((map1, map2) -> map2.values().iterator().next().compareTo(map1.values().iterator().next()));

        return array;
    }

}

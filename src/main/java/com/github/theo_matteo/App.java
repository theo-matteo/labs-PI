package com.github.theo_matteo;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Map<String, Double>> list = DataProcessor.getDataSummary(2017, Genero.MASCULINO);
        for (Map<String, Double> item : list) {
            for (Entry<String, Double> entry : item.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}

package com.db.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class Domain {

    @Autowired
    WeatherRepository weatherRepository;

    public void importWeatherDB() {
        weatherRepository.deleteAll();
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("GlobalWeatherRepository.csv")))) {

            boolean start = true;
            String line;
            while ((line = reader.readLine()) != null) {
                if (!start) {
                    String[] parts = line.split(",");

                    WeatherEntity weatherEntity = new WeatherEntity();
                    weatherEntity.country = parts[0];
                    weatherEntity.lastUpdated = parts[6];
                    weatherEntity.air_quality_Carbon_Monoxide = Double.parseDouble(parts[27]);
                    weatherEntity.air_quality_Ozone = Double.parseDouble(parts[28]);
                    weatherEntity.air_quality_Nitrogen_dioxide = Double.parseDouble(parts[29]);
                    weatherEntity.air_quality_Sulphur_dioxide = Double.parseDouble(parts[30]);
                    weatherEntity.air_quality_PM2_5 = Double.parseDouble(parts[31]);
                    weatherEntity.air_quality_PM10 = Double.parseDouble(parts[32]);
                    weatherEntity.air_quality_us_epa_index = Double.parseDouble(parts[33]);
                    weatherEntity.air_quality_gb_defra_index = Double.parseDouble(parts[34]);
                    weatherRepository.save(weatherEntity);
                }else {
                    start = false;
                }
            }
            System.out.println("Import complete.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWeatherForecast(ArrayList<String> address)   {
        return weatherRepository.findByCountryAndLastUpdated(address.get(0),address.get(1)).toString();
//        return "hello from getWeatherForecast";
    }

}

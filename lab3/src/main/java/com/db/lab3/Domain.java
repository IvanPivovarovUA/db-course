package com.db.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Domain {

    @Autowired
    WeatherRepository weatherRepository;

    public void importWeatherDB() {
        System.out.println("wwwwwwwwwwwwwwww");
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.country = "test";
        weatherEntity.air_quality_Carbon_Monoxide = 4.3;
        weatherRepository.save(weatherEntity);
        System.out.println("wwwwwwwwwwwwwwww");
    }
}

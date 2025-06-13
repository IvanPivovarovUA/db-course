package com.db.lab3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    List<WeatherEntity> findByCountryAndLastUpdated(String country, String last_update);

}

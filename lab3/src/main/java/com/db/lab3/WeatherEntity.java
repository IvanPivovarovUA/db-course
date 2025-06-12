package com.db.lab3;

import jakarta.persistence.*;

@Entity
@Table(name = "weather")
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long user_id;

    public String country;
    public String last_updated;
    public Double air_quality_Carbon_Monoxide;
    public Double air_quality_Ozone;
    public Double air_quality_Nitrogen_dioxide;
    public Double air_quality_Sulphur_dioxide;
    public Double air_quality_PM2_5;
    public Double air_quality_PM10;
    public Double air_quality_us_epa_index;
    public Double air_quality_gb_defra_index;
}

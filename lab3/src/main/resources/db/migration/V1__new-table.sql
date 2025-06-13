CREATE TABLE weather (
    USER_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    country VARCHAR,
    last_updated VARCHAR,
    air_quality_Carbon_Monoxide FLOAT,
    air_quality_Ozone FLOAT,
    air_quality_Nitrogen_dioxide FLOAT,
    air_quality_Sulphur_dioxide FLOAT,
    air_quality_PM2_5 FLOAT,
    air_quality_PM10 FLOAT,
    air_quality_us_epa_index FLOAT,
    air_quality_gb_defra_index FLOAT,

    go_outside BOOLEAN GENERATED ALWAYS AS (
        CASE
            WHEN air_quality_PM10 > 50 OR air_quality_Ozone > 100 THEN FALSE
            ELSE TRUE
        END
    ) STORED
);


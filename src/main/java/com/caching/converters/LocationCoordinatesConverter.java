package com.caching.converters;

import com.caching.models.LocationCoordinates;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

/**
 * The type Location coordinates converter.
 */
@Component
public class LocationCoordinatesConverter implements Converter<String, LocationCoordinates> {

    @Override
    public LocationCoordinates convert(String value) {
        if (value == null || !value.contains(",")) {
            throw new IllegalArgumentException("Invalid format for LocationCoordinates. Expected 'latitude,longitude'");
        }

        try {
            String[] parts = value.split(",");
            Double latitude = Double.parseDouble(parts[0]);
            Double longitude = Double.parseDouble(parts[1]);
            return new LocationCoordinates(latitude, longitude);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse latitude and longitude. Ensure they are valid numbers.", e);
        }
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(String.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(LocationCoordinates.class);
    }
}

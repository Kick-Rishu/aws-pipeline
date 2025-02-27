package com.caching.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * The type Location coordinates.
 */
@Data
public class LocationCoordinates {

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    /**
     * Instantiates a new Location coordinates.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public LocationCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Instantiates a new Location coordinates.
     */
    public LocationCoordinates(){

    }

    public List<Double> getList(){
        return List.of(latitude, longitude);
    }
}

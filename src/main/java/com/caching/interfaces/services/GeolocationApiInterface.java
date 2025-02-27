package com.caching.interfaces.services;

import com.caching.models.Address;
import com.caching.models.LocationCoordinates;

/**
 * The interface Geolocation api interface.
 */
public interface GeolocationApiInterface {
    /**
     * Gets forward geocoding.
     *
     * @param address the address
     * @return the forward geocoding
     */
    LocationCoordinates getForwardGeocoding(Address address) ;

    /**
     * Gets reverse geocoding.
     *
     * @param coordinates the coordinates
     * @return the reverse geocoding
     */
    String getReverseGeocoding(LocationCoordinates coordinates) ;
}

package com.caching.controllers;

import com.caching.interfaces.services.GeolocationApiInterface;
import com.caching.models.Address;
import com.caching.models.LocationCoordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * The type Location controller.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class LocationController {

    @Autowired
    private final GeolocationApiInterface geolocationApi;

    /**
     * Geocoding response entity.
     *
     * @param address the address
     * @return the response entity
     */
    @GetMapping("/geocoding")
    public ResponseEntity<LocationCoordinates> geocoding(@RequestParam Address address) {
        log.info("Geocoding {}", address);
        LocationCoordinates locationCoordinates = geolocationApi.getForwardGeocoding(address);
        log.info("Geocoding {}", locationCoordinates);
        return ResponseEntity.ok(locationCoordinates);
    }

    /**
     * Reverse geocoding response entity.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the response entity
     */
//    @GetMapping("/reverse-geocoding")
//    public ResponseEntity<String> reverseGeocoding(
//            @RequestParam String latitude, @RequestParam String longitude) {
//        log.info("Reverse geocoding {}, {}", latitude, longitude);
//        LocationCoordinates locationCoordinates = new LocationCoordinates(Double.parseDouble(latitude), Double.parseDouble(longitude));
//        log.info("Reverse geocoding {}, {}", locationCoordinates.getLongitude(), locationCoordinates.getLatitude());
//        Address address = geolocationApi.getReverseGeocoding(locationCoordinates);
//        log.info("Reverse geocoding address : {}", address);
//        return ResponseEntity.ok(address.getLabel());
//    }
    @GetMapping("/reverse-geocoding")
    public ResponseEntity<String> reverseGeocoding(
            @RequestParam double latitude, @RequestParam double longitude) {
        log.info("Reverse geocoding {}, {}", latitude, longitude);
        LocationCoordinates locationCoordinates = new LocationCoordinates(latitude, longitude);
        log.info("Reverse geocoding {}, {}", locationCoordinates.getLongitude(), locationCoordinates.getLatitude());
        String address = geolocationApi.getReverseGeocoding(locationCoordinates);
        log.info("Reverse geocoding address : {}", address);
        return ResponseEntity.ok(address);
    }
}

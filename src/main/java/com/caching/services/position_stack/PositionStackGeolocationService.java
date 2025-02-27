package com.caching.services.position_stack;

import com.caching.adapters.position_stack.PositionStackDataAdapter;
import com.caching.apis.PositionStackApi;
import com.caching.exceptions.ApiResponseParsingException;
import com.caching.interfaces.services.GeolocationApiInterface;
import com.caching.models.Address;
import com.caching.models.LocationCoordinates;
import com.caching.models.position_stack.PositionStackApiResponse;
import com.caching.utils.position_stack.parsers.PositionStackApiResponseParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpRequest;

import static com.caching.config.CachingConfig.FORWARD_GEOCODE;
import static com.caching.config.CachingConfig.REVERSE_GEOCODE;

/**
 * The type Position stack geolocation service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PositionStackGeolocationService implements GeolocationApiInterface {

    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final ApplicationContext applicationContext;


    /**
     * Gets forward geocoding.
     *
     * @param addressLabel the address label
     * @return the forward geocoding
     * @throws ApiResponseParsingException the api response parsing exception
     */
    public PositionStackApiResponse getForwardGeocoding(String addressLabel) throws ApiResponseParsingException {
        HttpRequest httpRequest = applicationContext.getBean(PositionStackApi.class)
                .forward(addressLabel)
                .build();
        ResponseEntity<String> response = restTemplate.getForEntity(httpRequest.uri().toString(), String.class);
        return PositionStackApiResponseParser.parseJson(response.getBody());
    }

    @Override
    @Cacheable(value=FORWARD_GEOCODE, key="#address.getLabel()", unless="#address.getLabel()=='goa'")
    public LocationCoordinates getForwardGeocoding(Address address) {
        PositionStackApiResponse positionStackApiResponse = getForwardGeocoding(address.getLabel());
        return PositionStackDataAdapter.toLocationCoordinates(positionStackApiResponse.getData()[0]);
    }

    /**
     * Gets reverse geocoding.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the reverse geocoding
     * @throws ApiResponseParsingException the api response parsing exception
     */
    public PositionStackApiResponse getReverseGeocoding(double latitude, double longitude) throws ApiResponseParsingException {
        HttpRequest httpRequest = applicationContext.getBean(PositionStackApi.class)
                .reverse(latitude, longitude)
                .build();
        log.error(httpRequest.uri().toString());
        ResponseEntity<String> response = restTemplate.getForEntity(httpRequest.uri().toString(), String.class);
        return PositionStackApiResponseParser.parseJson(response.getBody());
    }

    @Cacheable(value=REVERSE_GEOCODE, key="#coordinates.getList()")
    @Override
    public String getReverseGeocoding(LocationCoordinates coordinates) throws ApiResponseParsingException {
        PositionStackApiResponse positionStackApiResponse = getReverseGeocoding(
                coordinates.getLongitude(), coordinates.getLatitude());
        return PositionStackDataAdapter.toAddress(positionStackApiResponse.getData()[0]).getLabel();
    }

}

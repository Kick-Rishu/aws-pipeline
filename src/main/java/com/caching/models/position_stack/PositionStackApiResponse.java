package com.caching.models.position_stack;

import com.caching.adapters.position_stack.PositionStackDataAdapter;
import com.caching.models.Address;
import com.caching.models.ApiResponse;
import com.caching.models.LocationCoordinates;
import com.caching.utils.position_stack.parsers.PositionStackApiResponseParser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * The type Position stack api response.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionStackApiResponse extends ApiResponse {
    private PositionStackApiData[] data;

    /**
     * Create response from json position stack api response.
     *
     * @param jsonResponse the json response
     * @return the position stack api response
     */
    public static PositionStackApiResponse createResponseFromJson(String jsonResponse) {
        return PositionStackApiResponseParser.parseJson(jsonResponse);
    }

    @Override
    public Address getAddress() {
        return PositionStackDataAdapter.toAddress(data[0]);
    }

    @Override
    public LocationCoordinates getCoordinates() {
        return PositionStackDataAdapter.toLocationCoordinates(data[0]);
    }

    @Override
    public String toString() {
        return "PositionStackApiResponse{" + "data=" + Arrays.toString(data) +
                '}';
    }
}

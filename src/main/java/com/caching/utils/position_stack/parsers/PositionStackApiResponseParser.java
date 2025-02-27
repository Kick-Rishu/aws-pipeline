package com.caching.utils.position_stack.parsers;

import com.caching.exceptions.ApiResponseParsingException;
import com.caching.models.position_stack.PositionStackApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


/**
 * The type Position stack api response parser.
 */
@Slf4j
public class PositionStackApiResponseParser {

    private PositionStackApiResponseParser(){
        throw new UnsupportedOperationException();
    }

    /**
     * Parse json position stack api response.
     *
     * @param json the json
     * @return the position stack api response
     * @throws ApiResponseParsingException the api response parsing exception
     */
    public static PositionStackApiResponse parseJson(String json) throws ApiResponseParsingException {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, PositionStackApiResponse.class);
        } catch (RuntimeException | JsonProcessingException e) {
            log.error("Error parsing PositionStack API response: {}", e.getMessage());
            throw new ApiResponseParsingException(json, e);
        }
    }
}

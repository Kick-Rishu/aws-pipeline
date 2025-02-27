package com.caching.models.position_stack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * The type Position stack api data.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionStackApiData {
    private Double latitude;
    private Double longitude;
    private String label;
}

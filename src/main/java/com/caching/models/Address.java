package com.caching.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Address.
 */
@Getter
@Setter
@Data
public class Address {
    @JsonProperty("address")
    private String label;

    /**
     * Instantiates a new Address.
     *
     * @param label the label
     */
    public Address(String label) {
        this.label = label;
    }

    /**
     * Instantiates a new Address.
     */
    public Address() {
    }
}

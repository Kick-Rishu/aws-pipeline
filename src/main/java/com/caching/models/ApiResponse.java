package com.caching.models;


/**
 * The type Api response.
 */
public abstract class ApiResponse {
    /**
     * Gets address.
     *
     * @return the address
     */
    public abstract Address getAddress();

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public abstract LocationCoordinates getCoordinates();
}

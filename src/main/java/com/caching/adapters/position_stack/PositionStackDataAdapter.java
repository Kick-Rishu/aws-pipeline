package com.caching.adapters.position_stack;

import com.caching.models.Address;
import com.caching.models.LocationCoordinates;
import com.caching.models.position_stack.PositionStackApiData;

/**
 * The type Position stack data adapter.
 */
public class PositionStackDataAdapter {

    private PositionStackDataAdapter(){
        throw new UnsupportedOperationException();
    }

    /**
     * To location coordinates.
     *
     * @param positionStackApiData the position stack api data
     * @return the location coordinates
     */
    public static LocationCoordinates toLocationCoordinates(PositionStackApiData positionStackApiData) {
        LocationCoordinates locationCoordinates = new LocationCoordinates();
        locationCoordinates.setLatitude(positionStackApiData.getLatitude());
        locationCoordinates.setLongitude(positionStackApiData.getLongitude());
        return locationCoordinates;
    }

    /**
     * To address.
     *
     * @param positionStackApiData the position stack api data
     * @return the address
     */
    public static Address toAddress(PositionStackApiData positionStackApiData){
        Address address = new Address();
        address.setLabel(positionStackApiData.getLabel());
        return address;
    }

}

package com.yonder.tss.client;

import com.yonder.tss.exception.ResourceNotFoundException;

public interface Client<RESPONSE> {
    /**
     * The method fetches data from an external system
     *
     * @param resourceIdentifier resource identifier for which the data will be fetched
     * @return HTTP response body
     * @throws ResourceNotFoundException in case that there is no data found
     */
    RESPONSE fetchData(String resourceIdentifier) throws ResourceNotFoundException;
}

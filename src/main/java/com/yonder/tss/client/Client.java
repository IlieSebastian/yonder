package com.yonder.tss.client;

import com.yonder.tss.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public interface Client<RESPONSE> {
    /**
     * The method fetches data from an external system
     *
     * @return HTTP response mapped as an ResponseEntity object
     * @throws ResourceNotFoundException in case that there is no data found
     */
    ResponseEntity<RESPONSE> getData() throws ResourceNotFoundException;
}

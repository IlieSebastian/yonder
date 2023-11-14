package com.yonder.tss.service;

public interface DataService {
    /**
     * The method retrieves the data for a resource identifier
     *
     * @param resourceIdentifier - resource identifier
     * @param <R>                - data type
     * @return data containing the searched resource
     */
    <R> R getData(String resourceIdentifier);
}

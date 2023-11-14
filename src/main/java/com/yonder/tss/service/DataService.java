package com.yonder.tss.service;

import java.util.List;

public interface DataService<T> {

    /**
     * The method retrieves the data for a list of resource ids after validating them
     *
     * @param resourceList - resource identifiers
     * @return a list containing the searched resources
     */
    List<T> getData(List<String> resourceList);

    /**
     * The method stores the data within a CSV file
     *
     * @param data - data to be stored
     */
    void storeData(List<T> data);
}

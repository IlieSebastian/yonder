package com.yonder.tss.service;

import java.util.List;

public interface DataService<RESPONSE> {

    /**
     * The method retrieves the data for a list of resource ids after validating them
     *
     * @param resourceList - resource identifiers
     * @return a list containing the searched resources
     */
    List<RESPONSE> getData(List<String> resourceList);
}

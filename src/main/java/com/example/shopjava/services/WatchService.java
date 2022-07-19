package com.example.shopjava.services;

import com.example.shopjava.entities.product.Watch;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WatchService {
    Map<String, List<String>> getWatchCharacteristics();

    List<Watch> getAllWatches();

    List<Watch> watches(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max);

    void saveWatch(Watch watch);
}

package com.example.shopjava.services.impl;

import com.example.shopjava.entities.product.Filters;
import com.example.shopjava.entities.product.Watch;
import com.example.shopjava.repos.FilterProductsRepo;
import com.example.shopjava.repos.WatchRepo;
import com.example.shopjava.services.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class WatchServiceImpl implements WatchService {

    @Autowired
    private WatchRepo watchRepository;

    @Autowired
    private FilterProductsRepo filterProductsRepo;

    @Override
    public Map<String, List<String>> getWatchCharacteristics() {
        Filters filters = new Filters();
        filters.initWatchChars();
        return filters.watchCharacteristics;
    }

    @Override
    @Transactional
    public List<Watch> getAllWatches() {
        return watchRepository.findAll();
    }

    @Override
    @Transactional
    public List<Watch> watches(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max) {
        return filterProductsRepo.filterWatches(filters, fullFilters, min, max);
    }

    @Override
    public void saveWatch(Watch watch) {
        watchRepository.save(watch);
    }
}

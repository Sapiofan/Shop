package com.example.shopjava.services.impl;

import com.example.shopjava.entities.product.Filters;
import com.example.shopjava.entities.product.Laptop;
import com.example.shopjava.repos.FilterProductsRepo;
import com.example.shopjava.repos.LaptopRepository;
import com.example.shopjava.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private FilterProductsRepo filterProductsRepo;

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public Map<String, List<String>> getLaptopCharacteristics() {
        Filters filters = new Filters();
        filters.initLaptopChars();
        return filters.laptopCharacteristics;
    }

    @Override
    @Transactional
    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    @Override
    @Transactional
    public List<Laptop> laptops(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max) {
        return filterProductsRepo.filterLaptops(filters, fullFilters, min, max);
    }

    @Override
    @Transactional
    public void saveLaptop(Laptop laptop) {
        laptopRepository.save(laptop);
    }
}

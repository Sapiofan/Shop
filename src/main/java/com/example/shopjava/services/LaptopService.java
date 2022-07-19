package com.example.shopjava.services;

import com.example.shopjava.entities.product.Laptop;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LaptopService {
    Map<String, List<String>> getLaptopCharacteristics();

    List<Laptop> getAllLaptops();

    List<Laptop> laptops(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max);

    void saveLaptop(Laptop laptop);
}

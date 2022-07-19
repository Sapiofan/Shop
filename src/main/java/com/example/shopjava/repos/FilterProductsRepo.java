package com.example.shopjava.repos;

import com.example.shopjava.entities.product.Laptop;
import com.example.shopjava.entities.product.Phone;
import com.example.shopjava.entities.product.Watch;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FilterProductsRepo {
    List<Phone> filterPhones(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max);

    List<Laptop> filterLaptops(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max);

    List<Watch> filterWatches(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max);
}

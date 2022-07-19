package com.example.shopjava.services;

import com.example.shopjava.entities.product.Phone;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PhoneService {
    Map<String, List<String>> getPhoneCharacteristics();

    List<Phone> phones(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max);

    List<Phone> getAllPhones();

    Phone getPhoneByName(String name);

    Phone getPhoneById(Long id);

    void savePhone(Phone phone);
}

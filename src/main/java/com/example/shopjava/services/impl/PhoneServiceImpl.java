package com.example.shopjava.services.impl;

import com.example.shopjava.entities.product.Filters;
import com.example.shopjava.entities.product.Phone;
import com.example.shopjava.repos.FilterProductsRepo;
import com.example.shopjava.repos.PhoneRepository;
import com.example.shopjava.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private FilterProductsRepo filterProductsRepo;

    @Override
    public Map<String, List<String>> getPhoneCharacteristics() {
        Filters filters = new Filters();
        filters.initPhoneChars();
        return filters.phoneCharacteristics;
    }

    @Override
    @Transactional
    public List<Phone> phones(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max) {
        return filterProductsRepo.filterPhones(filters, fullFilters, min, max);
    }

    @Override
    @Transactional
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    @Override
    @Transactional
    public Phone getPhoneByName(String name) {
        return phoneRepository.getPhoneByName(name);
    }

    @Override
    @Transactional
    public Phone getPhoneById(Long id) {
        return phoneRepository.getPhoneById(id);
    }

    @Override
    @Transactional
    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }
}

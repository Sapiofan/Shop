package com.example.shopjava.services.impl;

import com.example.shopjava.entities.product.Laptop;
import com.example.shopjava.entities.product.Phone;
import com.example.shopjava.entities.product.Watch;
import com.example.shopjava.services.FindMaxProduct;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MaxProductImpl implements FindMaxProduct {
    @Override
    public Phone min(List<Phone> phoneList) {
        if (!phoneList.isEmpty())
            return phoneList.stream().min(Comparator.comparingInt(Phone::getPrice)).get();
        return null;
    }

    @Override
    public Phone max(List<Phone> phones) {
        if (!phones.isEmpty())
            return phones.stream().max(Comparator.comparingInt(Phone::getPrice)).get();
        return null;
    }

    @Override
    public Laptop maxLaptop(List<Laptop> laptops) {
        if (!laptops.isEmpty())
            return laptops.stream().max(Comparator.comparingInt(Laptop::getPrice)).get();
        return null;
    }

    @Override
    public Watch maxWatch(List<Watch> watches) {
        if (!watches.isEmpty())
            return watches.stream().max(Comparator.comparingInt(Watch::getPrice)).get();
        return null;
    }

    @Override
    public boolean checkAuth(Authentication authentication) {
        return authentication != null && !authentication.getName().equals("anonymousUser");
    }

}

package com.example.shopjava.repos;

import com.example.shopjava.entities.product.Laptop;
import com.example.shopjava.entities.product.Phone;
import com.example.shopjava.entities.product.Watch;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface Utils {
    Phone min(List<Phone> phoneList);

    Phone max(List<Phone> phones);

    Laptop maxLaptop(List<Laptop> laptops);

    Watch maxWatch(List<Watch> watches);

    boolean checkAuth(Authentication authentication);
}

package com.example.shopjava.services;


import com.example.shopjava.entities.another.Career;
import org.springframework.data.domain.Page;

public interface CareerService {
    String addCareerUser(Career career);

    Page<Career> getCareers(int pageNum);

    void deleteCareerUserById(Long id);

    void deleteCareerUserByEmail(String email);
}

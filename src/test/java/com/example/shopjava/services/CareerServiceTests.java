package com.example.shopjava.services;

import com.example.shopjava.entities.another.Career;
import com.example.shopjava.repos.CareerRepository;
import com.example.shopjava.services.impl.CareerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CareerServiceTests {

    @InjectMocks
    private CareerServiceImpl careerService;

    @Mock
    CareerRepository careerRepository;

    @Test
    public void addExistedCareerUserTest(){
        Career career = new Career();
        career.setId(1l);
        career.setEmail("email");
        when(careerRepository.findByEmail("email")).thenReturn(career);

        String str = careerService.addCareerUser(career);

        Assertions.assertEquals("Sorry, but user with such email has already send request for job", str);
    }

    @Test
    public void addCareerUserTest(){
        Career career = new Career();
        career.setId(1l);
        career.setEmail("email");
        when(careerRepository.findByEmail("email")).thenReturn(null);

        when(careerRepository.save(career)).thenReturn(career);

        String str = careerService.addCareerUser(career);

        Assertions.assertEquals("Your request for job was successfully sent. Expect an email in 2-3 days", str);
    }

    @Test
    public void getCareersTest(){
        Career career1 = new Career();
        career1.setId(1l);
        career1.setEmail("email");

        Career career2 = new Career();
        career2.setId(2l);
        career2.setEmail("another email");

        List<Career> careerList = new ArrayList<>();
        careerList.add(career1);
        careerList.add(career2);

        Page<Career> careerPage = new PageImpl<>(careerList);

        Pageable pageable = PageRequest.of(0, 4);

        when(careerRepository.findAll(pageable)).thenReturn(careerPage);

        Page<Career> careerPage1 = careerService.getCareers(1);

        Assertions.assertEquals(2, careerPage1.getTotalElements());
        Assertions.assertEquals(1, careerPage1.getTotalPages());
    }

    @Test
    public void deleteCareerByEmail(){
        careerService.deleteCareerUserByEmail("email");
        verify(careerRepository).deleteByEmail("email");
    }

    @Test
    public void deleteCareerById(){
        careerService.deleteCareerUserById(1l);
        verify(careerRepository).deleteById(1l);
    }
}

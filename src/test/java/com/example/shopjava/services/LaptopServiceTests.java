package com.example.shopjava.services;

import com.example.shopjava.entities.product.Laptop;
import com.example.shopjava.repos.LaptopRepo;
import com.example.shopjava.services.impl.LaptopServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LaptopServiceTests {

    @InjectMocks
    private LaptopServiceImpl laptopService;

    @Mock
    LaptopRepo laptopRepository;

    List<Laptop> laptops = new ArrayList<>();

    @BeforeEach
    public void laptopList(){
        Laptop l1 = new Laptop();
        l1.setId(1l);
        l1.setPrice(20000);
        l1.setCores(8);

        Laptop l2 = new Laptop();
        l2.setId(2l);
        l2.setPrice(10000);
        l2.setCores(2);

        laptops.add(l1);
        laptops.add(l2);
    }

    @Test
    public void saveLaptopTest(){

        Laptop laptop = new Laptop();
        laptop.setId(1l);
        laptop.setName("laptop");

        when(laptopRepository.save(laptop)).thenReturn(laptop);

        laptopService.saveLaptop(laptop);

        verify(laptopRepository).save(laptop);
    }

    @Test
    public void getAllLaptops(){

        when(laptopRepository.findAll()).thenReturn(laptops);

        List<Laptop> laptops2 = laptopService.getAllLaptops();

        Assertions.assertAll(() -> {
            assertEquals(1l, laptops2.get(0).getId());
            assertEquals(2l, laptops2.get(1).getId());
            assertEquals(20000, laptops2.get(0).getPrice());
            assertEquals(10000, laptops2.get(1).getPrice());
        });
    }
}

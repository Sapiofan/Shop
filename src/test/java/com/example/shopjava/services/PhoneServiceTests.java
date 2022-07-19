package com.example.shopjava.services;

import com.example.shopjava.entities.product.Phone;
import com.example.shopjava.entities.product.Product;
import com.example.shopjava.repos.PhoneRepository;
import com.example.shopjava.repos.ProductRepository;
import com.example.shopjava.services.impl.FilterProductsImpl;
import com.example.shopjava.services.impl.PhoneServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
public class PhoneServiceTests {
    @InjectMocks
    private PhoneServiceImpl phoneService;

    @Mock
    PhoneRepository phoneRepository;

    List<Phone> phones = new ArrayList<>();

    @BeforeEach
    public void phoneList(){
        Phone p1 = new Phone();
        p1.setId(1l);
        p1.setPrice(20000);
        p1.setCores(8);

        Phone p2 = new Phone();
        p2.setId(2l);
        p2.setPrice(10000);
        p2.setCores(2);

        phones.add(p1);
        phones.add(p2);
    }

    @Test
    public void savePhoneTest(){

        Phone phone = new Phone();
        phone.setId(1l);
        phone.setName("phone");

        when(phoneRepository.save(phone)).thenReturn(phone);

        phoneService.savePhone(phone);

        verify(phoneRepository).save(phone);
    }

    @Test
    public void getAllPhones(){

        when(phoneRepository.findAll()).thenReturn(phones);

        List<Phone> phones2 = phoneService.getAllPhones();

        Assertions.assertAll(() -> {
            assertEquals(1l, phones2.get(0).getId());
            assertEquals(2l, phones2.get(1).getId());
            assertEquals(20000, phones2.get(0).getPrice());
            assertEquals(10000, phones2.get(1).getPrice());
        });
    }

    @Test
    public void getPhoneByIdTest(){

        Phone phone = new Phone();
        phone.setId(1l);
        phone.setName("phone");

        when(phoneRepository.getPhoneById(1l)).thenReturn(phone);

        Phone phone1 = phoneService.getPhoneById(1l);

        Assertions.assertAll(() -> {
            assertEquals("phone", phone1.getName());
        });
    }

    @Test
    public void getPhoneByNameTest(){

        Phone phone = new Phone();
        phone.setId(1l);
        phone.setName("phone");

        when(phoneRepository.getPhoneByName("phone")).thenReturn(phone);

        Phone phone1 = phoneService.getPhoneByName("phone");

        Assertions.assertAll(() -> {
            assertEquals(1l, phone1.getId());
        });
    }
}

package com.example.shopjava.services;

import com.example.shopjava.entities.contacts.Contact;
import com.example.shopjava.entities.contacts.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {
    String addContactMessage(Contact contact, String subject, String message);

    String subs(String email);

    Page<Message> contacts(int pageNum);

    void deleteById(Long id);

    void deleteByEmail(String email);
}

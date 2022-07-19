package com.example.shopjava.services;

import com.example.shopjava.entities.contacts.Contact;
import com.example.shopjava.entities.contacts.Message;
import com.example.shopjava.repos.ContactRepository;
import com.example.shopjava.repos.MessageRepository;
import com.example.shopjava.services.impl.ContactServiceImpl;
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
public class ContactServiceTests {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    ContactRepository contactRepository;

    @Mock
    MessageRepository messageRepository;

    @Test
    public void addContactMessageTest(){
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setEmail("email");

        Message message1 = new Message();
        message1.setContact(contact);
        message1.setId(1l);
        message1.setMessage("message");
        message1.setSubject("subject");

        when(contactRepository.findByEmail("email")).thenReturn(contact);

        when(contactRepository.save(contact)).thenReturn(contact);

        String str = contactService.addContactMessage(contact, "subject", "message");

        Assertions.assertEquals("Message was successfully sent. We will answer during the week.", str);
    }

    @Test
    public void getContactsTest(){
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setEmail("email");

        Message message1 = new Message();
        message1.setContact(contact);
        message1.setId(1l);
        message1.setMessage("message");

        Message message2 = new Message();
        message2.setContact(contact);
        message2.setId(2l);
        message2.setMessage("message 2");

        List<Message> messageList = new ArrayList<>();
        messageList.add(message1);
        messageList.add(message2);

        Page<Message> messages = new PageImpl<>(messageList);

        Pageable pageable = PageRequest.of(0, 5);

        when(messageRepository.findAll(pageable)).thenReturn(messages);

        Page<Message> messagePage = contactService.contacts(1);

        Assertions.assertEquals(2, messagePage.getTotalElements());
        Assertions.assertEquals(1, messagePage.getTotalPages());
    }

    @Test
    public void deleteContactByEmail(){
        contactService.deleteByEmail("email");
        verify(contactRepository).deleteByEmail("email");
    }

    @Test
    public void deleteMessageById(){
        Contact contact = new Contact();
        contact.setId(1l);

        Message message = new Message();
        message.setId(1l);
        message.setContact(contact);

        List<Message> messages = new ArrayList<>();
        messages.add(message);
        contact.setMessages(messages);

        when(messageRepository.findMessageById(1l)).thenReturn(message);

        contactService.deleteById(1l);
        verify(contactRepository).deleteById(1l);
        verify(messageRepository).deleteById(1l);
    }
}

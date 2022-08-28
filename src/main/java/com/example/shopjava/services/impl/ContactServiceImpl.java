package com.example.shopjava.services.impl;

import com.example.shopjava.entities.contacts.Contact;
import com.example.shopjava.entities.contacts.Message;
import com.example.shopjava.repos.ContactRepository;
import com.example.shopjava.repos.MessageRepository;
import com.example.shopjava.services.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private MessageRepository messageRepository;

    private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Override
    @Transactional
    public String addContactMessage(Contact contact, String subject, String message) {
        Contact ContactInDb = contactRepository.findByEmail(contact.getEmail());
        Message message1 = new Message(subject, message);
        if (ContactInDb != null) {
            message1.setContact(ContactInDb);
            messageRepository.save(message1);
            contactRepository.save(ContactInDb);
            log.info("The contact exists. Message was added.");
        } else {
            message1.setContact(contact);
            messageRepository.save(message1);
            contactRepository.save(contact);
            log.warn("The contact doesn't exists. The Contact was created and the message was added.");
        }
        return "Message was successfully sent. We will answer during the week.";
    }

    @Override
    @Transactional
    public String subs(String email) {
        Contact contact = contactRepository.findByEmail(email);
        if (contact != null) {
            contact.setSendMails(true);
        } else {
            contact = new Contact();
            contact.setEmail(email);
            contact.setSendMails(true);
        }
        contactRepository.save(contact);
        return "From now you will get emails about special and beneficial offers.";
    }

    @Override
    @Transactional
    public Page<Message> contacts(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return messageRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Message message = messageRepository.findMessageById(id);
        Contact contact = message.getContact();
        contact.getMessages().remove(message);
        log.warn("Message was deleted: " + message);
        messageRepository.deleteById(id);
        if(contact.getMessages().isEmpty())
            contactRepository.deleteById(contact.getId());
        contactRepository.save(contact);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        contactRepository.deleteByEmail(email);
    }
}

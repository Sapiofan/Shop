package com.example.shopjava.entities.contacts;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String first_name;

    @Column(name = "surname")
    private String last_name;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact")
    List<Message> messages = new ArrayList<>();

    @Column(name = "send_mails")
    private Boolean sendMails;

    public Contact() {
    }

    public Contact(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSendMails() {
        return sendMails;
    }

    public void setSendMails(Boolean sendMails) {
        this.sendMails = sendMails;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(first_name, contact.first_name) && Objects.equals(last_name, contact.last_name) && Objects.equals(email, contact.email) && Objects.equals(sendMails, contact.sendMails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, sendMails);
    }
}

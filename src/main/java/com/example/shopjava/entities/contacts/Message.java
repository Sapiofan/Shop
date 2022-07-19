package com.example.shopjava.entities.contacts;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "contact_messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

    @Column(name = "sent_at")
    private Date sent = Date.from(Instant.now());

    @ManyToOne(fetch = FetchType.EAGER)
    private Contact contact;

    public Message() {
    }

    public Message(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        contact.addMessage(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(id, message1.id) && Objects.equals(subject, message1.subject) && Objects.equals(message, message1.message) && Objects.equals(sent, message1.sent) && Objects.equals(contact, message1.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, message, sent, contact);
    }

    @Override
    public String toString() {
        return "Message{" +
                "subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", sent=" + sent +
                ", contact=" + contact +
                '}';
    }
}

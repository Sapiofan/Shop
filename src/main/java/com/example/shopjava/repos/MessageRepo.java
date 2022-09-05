package com.example.shopjava.repos;

import com.example.shopjava.entities.contacts.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @Query("SELECT m from Message m where m.id = :id")
    Message findMessageById(Long id);
}

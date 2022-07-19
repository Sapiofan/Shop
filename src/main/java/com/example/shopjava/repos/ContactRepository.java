package com.example.shopjava.repos;

import com.example.shopjava.entities.contacts.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT c from Contact c where c.email = :email")
    Contact findByEmail(String email);

    @Query("delete from Contact c where c.id=:id")
    @Modifying
    void deleteById(Long id);

    @Query("delete from Contact c where c.email=:email")
    @Modifying
    void deleteByEmail(String email);
}

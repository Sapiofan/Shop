package com.example.shopjava.repos;

import com.example.shopjava.entities.another.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FaqRepository extends JpaRepository<FAQ, Long> {
    @Query("select f from FAQ f where f.question = :question")
    FAQ findByQuestion(String question);

    @Query("select f from FAQ f where f.id = :id")
    FAQ findFaqById(Long id);

    @Query("delete from FAQ f where f.id=:id")
    @Modifying
    void deleteById(Long id);

    @Query("delete from FAQ f where f.question=:question")
    @Modifying
    void deleteByQuestion(String question);
}

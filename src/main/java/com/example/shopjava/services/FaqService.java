package com.example.shopjava.services;

import com.example.shopjava.entities.another.FAQ;

import java.util.List;

public interface FaqService {
    String addFaq(FAQ faq);

    String editFaq(Long id, String question, String answer);

    List<FAQ> getFaqs();

    void deleteById(Long id);

    void deleteByQuestion(String question);
}

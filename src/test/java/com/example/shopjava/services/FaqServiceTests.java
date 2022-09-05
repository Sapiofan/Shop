package com.example.shopjava.services;

import com.example.shopjava.entities.another.FAQ;
import com.example.shopjava.repos.FaqRepo;
import com.example.shopjava.services.impl.FaqServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FaqServiceTests {

    @InjectMocks
    private FaqServiceImpl faqService;

    @Mock
    FaqRepo faqRepository;

    @Test
    public void addExistedFaqTest(){
        FAQ faq = new FAQ();
        faq.setQuestion("q");
        faq.setId(1l);

        when(faqRepository.findByQuestion("q")).thenReturn(faq);

        String str = faqService.addFaq(faq);

        Assertions.assertEquals("Such question has already added", str);
    }

    @Test
    public void addNewFaqTest(){
        FAQ faq = new FAQ();
        faq.setQuestion("q");
        faq.setId(1l);

        when(faqRepository.findByQuestion("q")).thenReturn(null);

        when(faqRepository.save(faq)).thenReturn(faq);

        String str = faqService.addFaq(faq);

        Assertions.assertEquals("Question was added", str);
    }

    @Test
    public void editFaqTest(){
        FAQ faq = new FAQ();
        faq.setQuestion("q");
        faq.setId(1l);


        when(faqRepository.findFaqById(1l)).thenReturn(faq);

        when(faqRepository.save(faq)).thenReturn(faq);

        String str = faqService.editFaq(1l, "new question", "new answer");

        Assertions.assertEquals("Was successfully edited", str);
    }

    @Test
    public void editFaqFailTest(){
        when(faqRepository.findFaqById(1l)).thenReturn(null);

        String str = faqService.editFaq(1l, "new question", "new answer");

        Assertions.assertEquals("Something went wrong. Such faq doesn't exist.", str);
    }

    @Test
    public void getFAQsTest(){
        FAQ faq1 = new FAQ();
        faq1.setId(1l);
        faq1.setQuestion("question");

        FAQ faq2 = new FAQ();
        faq2.setId(2l);
        faq2.setQuestion("question 2");

        List<FAQ> faqs = new ArrayList<>();
        faqs.add(faq1);
        faqs.add(faq2);

        when(faqRepository.findAll()).thenReturn(faqs);

        List<FAQ> faqs1 = faqService.getFaqs();

        Assertions.assertEquals(2, faqs1.size());
        Assertions.assertEquals("question", faqs1.get(0).getQuestion());
    }

    @Test
    public void deleteFaqByQuestion(){
        faqService.deleteByQuestion("q");
        verify(faqRepository).deleteByQuestion("q");
    }

    @Test
    public void deleteFaqById(){
        faqService.deleteById(1l);
        verify(faqRepository).deleteById(1l);
    }
}

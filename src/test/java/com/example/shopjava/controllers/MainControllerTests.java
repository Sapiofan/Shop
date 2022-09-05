//package com.example.shopjava.controllers;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
//public class MainControllerTests {
//    @Autowired
//    private MockMvc mvc;
//
//    private static final Logger log = LoggerFactory.getLogger(MainControllerTests.class);
//
//    @Test
//    public void loadHomePageTest() throws Exception {
//        this.mvc.perform(get("/"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void loadAboutPageTest() throws Exception {
//        this.mvc.perform(get("/about"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void searchNegativeTest() throws Exception {
//        this.mvc.perform(get("/searching")
//                        .param("search", "searchKey"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("products", hasSize(0)))
//                .andExpect(view().name("filters"))
//                .andReturn();
//    }
//
//    @Test
//    public void getPhonesPageTest() throws Exception {
//        this.mvc.perform(get("/phones"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("filters"));
//    }
//
//    @Test
//    public void negativeAndBigPricePostPhonesTest() throws Exception {
//        this.mvc.perform(post("/phones")
//                        .param("input-min", "100000")
//                        .param("input-max", "20000000")
//                        .param("sort", "From expensive to cheap"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("products", hasSize(0)));
//
//        this.mvc.perform(post("/phones")
//                        .param("input-min", "-500")
//                        .param("input-max", "0")
//                        .param("sort", "From expensive to cheap"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("filters"))
//                .andExpect(model().attribute("products", hasSize(0)));
//    }
//
//    @Test
//    public void getLaptopsPageTest() throws Exception {
//        this.mvc.perform(get("/laptops"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("filters"));
//    }
//
//    @Test
//    public void getWatchPageTest() throws Exception {
//        this.mvc.perform(get("/watches"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("filters"));
//    }
//
//    @Test
//    public void getDiscountPageTest() throws Exception {
//        this.mvc.perform(get("/discounts"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("filters"));
//    }
//
//    @Test
//    public void getBestsellersPageTest() throws Exception {
//        this.mvc.perform(get("/bestsellers"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("filters"));
//    }
//
//    @Test
//    public void loadContactPageTest() throws Exception {
//        this.mvc.perform(get("/contact"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("contact"));
//    }
//
//    @Test
//    public void loadHelpPageTest() throws Exception {
//        this.mvc.perform(get("/help"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("help"));
//    }
//}

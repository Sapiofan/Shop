package com.example.shopjava.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "somemail@gmail.com", password = "somePassword")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@CucumberContextConfiguration
//@CucumberOptions(features = "classpath: cucumber", glue = "com.example.shopjava.controllers")
//@RunWith(Cucumber.class)
public class CommandsTests {

    @Autowired
    private MockMvc mvc;

    private final MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    @Test
    @Order(1)
    public void addFavoriteTest() throws Exception {
        this.mvc.perform(get("/addProduct/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$", hasSize(1))).andReturn();
    }

    @Test
    @Order(2)
    public void deleteFavoriteTest() throws Exception {
        this.mvc.perform(get("/deleteFavorite/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @Order(3)
    public void addToCartTest() throws Exception {
        this.mvc.perform(get("/addToCart/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Order(4)
    public void increaseQuantityTest() throws Exception {
        this.mvc.perform(get("/addAdditionalProduct/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].quantity", is(2)));
    }

    @Test
    @Order(5)
    public void decreaseQuantityTest() throws Exception {
        this.mvc.perform(get("/subtractAdditionalProduct/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].quantity", is(1)));
    }

    @Test
    @Order(6)
    public void deleteFomCartTest() throws Exception {
        this.mvc.perform(get("/deleteCartProduct/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @Order(7)
    public void cleanWishlistTest() throws Exception {
        this.mvc.perform(get("/cleanWishlist"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$", hasSize(0)));
    }
}

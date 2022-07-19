package com.example.shopjava.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void accessDeniedIfNotAdmin() throws Exception {
        this.mvc.perform(get("/admin/faq"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @Transactional
    public void successfulLogin() throws Exception {
        this.mvc.perform(post("/")
                        .param("email", "somemail@gmail.com")
                        .param("psw", "somePassword")
                        .param("login", "login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(model().attribute("error", ""));
    }

    @Test
    public void badCredentials() throws Exception {
        this.mvc.perform(post("/")
                        .param("email", "somemail@gmail.com")
                        .param("psw", "badPassword")
                        .param("login", "login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(unauthenticated())
                .andExpect(model().attribute("error", "Password was wrong"));
    }

    @Test
    public void existedEmailWhileRegistration() throws Exception {
        this.mvc.perform(post("/")
                        .param("email", "somemail@gmail.com")
                        .param("psw", "badPassword")
                        .param("psw-repeat", "badPassword")
                        .param("registration", "registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(unauthenticated())
                .andExpect(model().attribute("error", "User with such email already exists"));
    }

    @Test
    @WithMockUser(username = "somemail@gmail.com", password = "somePassword")
    public void successfulLogout() throws Exception {
        this.mvc.perform(post("/")
                        .param("logout", "logout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(unauthenticated());
    }
}

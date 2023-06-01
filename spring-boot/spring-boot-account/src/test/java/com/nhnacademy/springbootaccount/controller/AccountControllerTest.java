package com.nhnacademy.springbootaccount.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springbootaccount.entity.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void getAccounts()throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].number",equalTo("나")));
    }

    @Test
    @Order(2)
    void getAccount() throws Exception {
        mockMvc.perform(get("/accounts/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("number",equalTo("나")));
    }

    @Test
    @Order(3)
    void createAccount() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = new Account(3L,"song",1000);
        mockMvc.perform(post("/accounts")
                        .content(objectMapper.writeValueAsString(account))
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number",equalTo("song")));
    }

    @Test
    @Order(4)
    void deleteAccount() throws Exception {
        this.mockMvc.perform(delete("/accounts/{id}", 3L))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("OK")));
    }
}
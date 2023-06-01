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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerRandomPortTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void testGetAccounts() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Account> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Account>> exchange = testRestTemplate.exchange(
                "/accounts",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Account>>() {
                });

        assertThat(exchange.getBody())
                .contains(new Account(1L, "나", 100));
    }
    @Test
    @Order(2)
    void testGetStudent() throws Exception{
        ResponseEntity<Account> result = testRestTemplate.getForEntity(
                "/accounts/{id}",
                Account.class,
                1L);

        assertThat(result.getBody())
                .isEqualTo(new Account(1L, "나", 100));
    }
    @Test
    @Order(3)
    void testCreateAccount() throws Exception{
        Account zbum = new Account(3L, "zbum1", 100);
        ResponseEntity<Account> result = testRestTemplate.postForEntity(
                "/accounts",
                zbum,
                Account.class);

        assertThat(result.getBody())
                .isEqualTo(zbum);
    }

    @Test
    @Order(4)
    void testDeleteAccount() throws Exception{
        testRestTemplate.delete(
                "/accounts/{id}",
                3L);
    }
}
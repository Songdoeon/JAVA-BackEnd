package com.nhnacademy.openapi.controller;

import com.nhnacademy.openapi.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OpenAccountController{

    private final RestTemplate restTemplate = new RestTemplate();
//    new ParameterizedTypeReference<>()


    @Value("${back-end.address}")
    String address;

    @GetMapping("/accounts")
    public String getAccounts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity res = restTemplate.exchange(address +
                "/accounts",
                HttpMethod.GET,
                entity,
                String.class);

        return (String) res.getBody();
    }
    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity<Account> res = restTemplate.exchange(address + "/accounts/{id}",
                HttpMethod.GET,
                entity,
                Account.class,
                id);


        return res.getBody();
    }

    @PostMapping("/accounts")
    public Account registerAccount(@RequestBody Account account) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(account.getId()));
        params.put("number", account.getNumber());
        params.put("balance", String.valueOf(account.getBalance()));

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<Account> res = restTemplate.exchange(address + "/accounts",
                HttpMethod.POST,
                entity,
                Account.class);


        return res.getBody();
    }

    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity res = restTemplate.exchange(address + "/accounts/" + id,
                HttpMethod.DELETE,
                entity,
                String.class);

        return res.getBody().equals("OK") ? "OK" : "";
    }
}
package com.nhnacademy.springbootaccount.controller;


import com.nhnacademy.springbootaccount.config.AccountProperties;
import com.nhnacademy.springbootaccount.domain.AccountDTO;
import com.nhnacademy.springbootaccount.entity.Account;
import com.nhnacademy.springbootaccount.service.DefaultAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final DefaultAccountService accountService;
    private final AccountProperties accountProperties;

    @GetMapping("/accounts")
    public List<Account> getStudents() {
        return accountService.getAccounts();
    }
    @GetMapping("/accounts/{id}")
    public AccountDTO getStudent(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createStudent(@RequestBody AccountDTO account) {
        return accountService.postAccount(account);
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteStudent(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "{\"result\":\"OK\"}";
    }

    @GetMapping("/system/version")
    public String response(){
        return accountProperties.getVersion();
    }

}

package com.nhnacademy.springbootaccount.controller;


import com.nhnacademy.springbootaccount.entity.Account;
import com.nhnacademy.springbootaccount.service.AccountService;
import com.nhnacademy.springbootaccount.service.DefaultAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final DefaultAccountService accountService;

    @GetMapping("/students")
    public List<Account> getStudents() {
        return accountService.getAccounts();
    }
    @GetMapping("/students/{id}")
    public Account getStudent(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createStudent(@RequestBody Account account) {
        return accountService.postAccount(account);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteStudent(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "{\"result\":\"OK\"}";
    }

}

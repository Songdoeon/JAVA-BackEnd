package com.nhnacademy.springbootaccount.controller;

import com.nhnacademy.springbootaccount.domain.AccountDTO;
import com.nhnacademy.springbootaccount.entity.Account;
import com.nhnacademy.springbootaccount.service.DefaultAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AccountViewController {

    private final DefaultAccountService service;
    @GetMapping("/web/account")
    public String getStudent(Model model){
        model.addAttribute("account", new Account(3L,"son", 1000));
        return "account";
    }
    @GetMapping("/web/account/{id}")
    public String getId(@PathVariable Long id,
                             Model model){

        AccountDTO account = service.getAccount(id);
        model.addAttribute("account", account);
        return "account";
    }
}

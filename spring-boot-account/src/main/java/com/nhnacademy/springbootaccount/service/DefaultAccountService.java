package com.nhnacademy.springbootaccount.service;

import com.nhnacademy.springbootaccount.entity.Account;
import com.nhnacademy.springbootaccount.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
@Transactional
public class DefaultAccountService implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id){
        return accountRepository.findById(id).orElseThrow();
    }

    public Account postAccount(Account account){
        Account addAccount = account;
        accountRepository.saveAndFlush(addAccount);

        return addAccount;
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}

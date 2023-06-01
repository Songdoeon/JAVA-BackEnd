package com.nhnacademy.springbootaccount.service;

import com.nhnacademy.springbootaccount.domain.AccountDTO;
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

    public AccountDTO getAccount(Long id){
        Account account = accountRepository.findById(id).orElseThrow();


        return new AccountDTO(account.getId(),account.getNumber(),account.getBalance());
    }

    public AccountDTO postAccount(AccountDTO accountDTO){
        Account account = new Account().builder()
                .id(accountDTO.getId())
                .number(accountDTO.getNumber())
                .balance(accountDTO.getBalance())
                .build();

        accountRepository.saveAndFlush(account);

        return new AccountDTO(account.getId(),account.getNumber(),account.getBalance());
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}

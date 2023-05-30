package com.nhnacademy.springbootaccount.service;

import com.nhnacademy.springbootaccount.entity.Account;
import com.nhnacademy.springbootaccount.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DefaultAccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void getAccounts() {
        Account account = new Account(1L,"나",100);
        accountRepository.save(account);

        Optional<Account> actual =accountRepository.findById(1L);

        Assertions.assertThat(actual).isPresent();
        Assertions.assertThat(actual.get()).isEqualTo(account);
    }
}
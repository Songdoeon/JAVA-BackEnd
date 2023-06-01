package com.nhnacademy.springbootaccount.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AccountDTO {
    private Long id;
    private String number;
    private int balance;
}

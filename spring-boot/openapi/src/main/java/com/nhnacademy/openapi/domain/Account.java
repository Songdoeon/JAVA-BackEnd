package com.nhnacademy.openapi.domain;

import lombok.*;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;
    private String number;
    private int balance;

}

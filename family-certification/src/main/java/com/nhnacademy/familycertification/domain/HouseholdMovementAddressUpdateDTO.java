package com.nhnacademy.familycertification.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class HouseholdMovementAddressUpdateDTO {
    private String houseMovementAddress;
}
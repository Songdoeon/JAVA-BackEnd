package com.nhnacademy.familycertification.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class HouseholdMovementAddressDTO {
    private LocalDate houseMovementReportDate;
    private String houseMovementAddress;
    private char lastAddressYn;

}
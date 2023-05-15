package com.nhnacademy.familycertification.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HouseholdMovementAddressDTO {

    private Long householdSerialNumber;
    private LocalDateTime houseMovementReportDate;
    private String houseMovementAddress;

}
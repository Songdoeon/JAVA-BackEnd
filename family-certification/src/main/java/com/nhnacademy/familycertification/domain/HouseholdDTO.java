package com.nhnacademy.familycertification.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HouseholdDTO {

    private Long householdResidentSerialNumber;
    private LocalDateTime householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;

}
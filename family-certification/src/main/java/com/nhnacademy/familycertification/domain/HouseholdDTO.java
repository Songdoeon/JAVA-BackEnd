package com.nhnacademy.familycertification.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class HouseholdDTO {

    private Long householdResidentSerialNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;

}
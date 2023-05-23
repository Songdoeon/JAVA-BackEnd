package com.nhnacademy.familycertification.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResidentRegisterDTO {

    private String name;
    private String residentRegistrationNumber;
    private String genderCode;
    private String residentId;
    private String password;
    private LocalDateTime birthDate;
    private String birthPlaceCode;
    private String registrationBaseAddress;
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;

}
package com.nhnacademy.familycertification.domain.birthdeath;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeathReportUpdateDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDeathReportDate;
    private String emailAddress;
    private String phoneNumber;
}
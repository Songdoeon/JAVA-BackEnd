package com.nhnacademy.familycertification.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateIssueDTO {

    private Long certificateConfirmationNumber;
    private String certificateTypeCode;
    private LocalDateTime certificateIssueDate;

}
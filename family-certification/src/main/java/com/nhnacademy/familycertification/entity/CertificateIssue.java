package com.nhnacademy.familycertification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "certificate_issue")
public class CertificateIssue {

    @Id
    @Column(name = "certificate_confirmation_number")
    private Long certificateConfirmationNumber;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "certificate_type_code")
    private String certificateTypeCode;
//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;
}
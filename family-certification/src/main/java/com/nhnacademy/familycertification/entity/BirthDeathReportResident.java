package com.nhnacademy.familycertification.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {

    @EmbeddedId
    private Pk pk;

    // 출생 or 사망한 사람
    @MapsId("residentSerialNumber")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    // 신고한 사람
    @MapsId("reportResidentSerialNumber")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResidentSerialNumber;

    @Setter
    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;

    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;

    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;
    @Setter
    @Column(name = "email_address")
    private String emailAddress;
    @Setter
    @Column(name = "phone_number")
    private String phoneNumber;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "resident_serial_number")
        private Long residentSerialNumber;
        @Column(name = "report_resident_serial_number")
        private Long reportResidentSerialNumber;
        @Column(name = "birth_death_type_code")
        private String birthDeathTypeCode;
    }

}
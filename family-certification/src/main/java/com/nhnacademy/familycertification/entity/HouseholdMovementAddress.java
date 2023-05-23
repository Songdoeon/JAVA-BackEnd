package com.nhnacademy.familycertification.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {

    @EmbeddedId
    private Pk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @Setter
    @Column(name = "house_movement_address")
    private String houseMovementAddress;

    @Setter
    @Column(name = "last_address_yn")
    private char lastAddressYn;

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "household_serial_number")
        private Long householdSerialNumber;

        @Column(name = "house_movement_report_date")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate houseMovementReportDate;
    }
}

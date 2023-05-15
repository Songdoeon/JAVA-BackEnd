package com.nhnacademy.familycertification.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "household")
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Long householdSerialNumber;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident householdResidentSerialNumber;

    @Column(name = "household_composition_date")
    private LocalDateTime householdCompositionDate;

    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "household", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<HouseholdCompositionResident> householdCompositionResidents;

    @JsonIgnore
    @OneToMany(mappedBy = "household", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<HouseholdMovementAddress> householdMovementAddresses;
}
package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress,HouseholdMovementAddress.Pk> {

    Optional<HouseholdMovementAddress> findByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(LocalDate date, Long number);

    Optional<List<HouseholdMovementAddress>> findByPk_householdSerialNumber(Long number);
}

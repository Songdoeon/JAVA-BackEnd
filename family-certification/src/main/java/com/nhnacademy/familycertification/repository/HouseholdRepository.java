package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseholdRepository extends JpaRepository<Household, Long> {

    @Query("SELECT H FROM Household H WHERE H.householdResidentSerialNumber.residentSerialNumber = ?1")
    Household findByResidentSerialNumber(Long residentSerialNumber);
}

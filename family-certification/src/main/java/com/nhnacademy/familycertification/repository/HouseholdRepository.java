package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.entity.Household;
import com.nhnacademy.familycertification.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseholdRepository extends JpaRepository<Household, Long> {

    Household findByHouseholdResidentSerialNumber_ResidentSerialNumber(Long residentSerialNumber);

    Household findByHouseholdResidentSerialNumber_ResidentSerialNumber(Resident resident);
}

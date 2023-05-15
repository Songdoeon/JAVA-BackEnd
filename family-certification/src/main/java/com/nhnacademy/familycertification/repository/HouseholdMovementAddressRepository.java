package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress,HouseholdMovementAddress.Pk> {
}

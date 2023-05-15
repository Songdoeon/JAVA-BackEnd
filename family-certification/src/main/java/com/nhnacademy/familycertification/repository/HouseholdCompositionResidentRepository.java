package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident,HouseholdCompositionResident.Pk> {
}

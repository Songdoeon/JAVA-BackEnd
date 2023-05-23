package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident,HouseholdCompositionResident.Pk> {

    Optional<List<HouseholdCompositionResident>> findByPk_HouseholdSerialNumberAndPk_ResidentSerialNumber(Long householdNum, Long ResidentNum);

    @Query("SELECT H FROM HouseholdCompositionResident H WHERE H.pk.householdSerialNumber = ?1")
    Optional<List<HouseholdCompositionResident>> getComposition(Long householdNum);
}

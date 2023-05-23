package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.domain.familyrelationship.FamilyRelationshipDTO;
import com.nhnacademy.familycertification.entity.FamilyRelationship;
import net.minidev.json.JSONUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship,FamilyRelationship.Pk> {

    @Query("select f from FamilyRelationship f where f.pk.familyResidentSerialNumber= ?1 and f.pk.baseResidentSerialNumber = ?2")
    Optional<FamilyRelationship> findByPk_FamilyResidentSerialNumberAndPk_BaseResidentSerialNumber (Long targetNumber,Long baseNumber);

    Optional<List<FamilyRelationship>> findByPk_BaseResidentSerialNumber(Long serialNumber);
    Optional<FamilyRelationship> findByPk_BaseResidentSerialNumberAndFamilyRelationshipCode(Long id,String code);

}

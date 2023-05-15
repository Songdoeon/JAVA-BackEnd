package com.nhnacademy.familycertification.repository;

import com.nhnacademy.familycertification.domain.FamilyRelationshipDTO;
import com.nhnacademy.familycertification.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship,FamilyRelationship.Pk> {

    List<FamilyRelationshipDTO> findByPkFamilyResidentSerialNumber(Long serialNumber);
}

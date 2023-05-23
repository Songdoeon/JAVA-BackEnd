package com.nhnacademy.familycertification.service;


import com.nhnacademy.familycertification.domain.familyrelationship.FamilyRelationshipDTO;
import com.nhnacademy.familycertification.domain.familyrelationship.FamilyRelationshipUpdateDTO;
import com.nhnacademy.familycertification.entity.FamilyRelationship;
import com.nhnacademy.familycertification.entity.Resident;
import com.nhnacademy.familycertification.exception.NotFoundResidentException;
import com.nhnacademy.familycertification.repository.FamilyRelationshipRepository;
import com.nhnacademy.familycertification.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationshipDTO registerFamilyRelationship(FamilyRelationshipDTO familyRelationshipDTO, Long baseNumber) {

        Resident resident = residentRepository.findById(baseNumber).orElseThrow(NotFoundResidentException::new);
        Resident familyResident = residentRepository.findById(familyRelationshipDTO.getFamilySerialNumber()).orElseThrow(NotFoundResidentException::new);

        FamilyRelationship familyRelationship = new FamilyRelationship().builder()
                .pk(new FamilyRelationship.Pk(baseNumber,familyRelationshipDTO.getFamilySerialNumber()))
                .resident(resident)
                .familyResident(familyResident)
                .familyRelationshipCode(familyRelationshipDTO.getFamilyRelationshipCode())
                .build();
        familyRelationshipRepository.saveAndFlush(familyRelationship);
        return familyRelationshipDTO;
    }
    public FamilyRelationshipUpdateDTO updateFamilyRelationship(FamilyRelationshipUpdateDTO familyRelationshipDTO, Long baseNumber, Long targetNumber) {
        FamilyRelationship familyRelationship = familyRelationshipRepository.findByPk_FamilyResidentSerialNumberAndPk_BaseResidentSerialNumber(
                targetNumber,baseNumber).orElseThrow(NotFoundResidentException::new);

        familyRelationship.setFamilyRelationshipCode(familyRelationshipDTO.getFamilyRelationshipCode());
        familyRelationshipRepository.saveAndFlush(familyRelationship);
        return familyRelationshipDTO;
    }
    public void deleteFamilyRelationship(Long baseNumber, Long targetNumber) {
        FamilyRelationship familyRelationship = familyRelationshipRepository.findByPk_FamilyResidentSerialNumberAndPk_BaseResidentSerialNumber(
                targetNumber,baseNumber).orElseThrow(NotFoundResidentException::new);

        familyRelationshipRepository.delete(familyRelationship);
    }
    public List<FamilyRelationship> getFamilyRelationship(Long number){
        return familyRelationshipRepository.findByPk_BaseResidentSerialNumber(number).orElseThrow(NotFoundResidentException::new);
    }

    public FamilyRelationship getFather(Long number){
        return familyRelationshipRepository.findByPk_BaseResidentSerialNumberAndFamilyRelationshipCode(number,"부").orElseThrow(NotFoundResidentException::new);
    }
    public FamilyRelationship getMother(Long number){
        return familyRelationshipRepository.findByPk_BaseResidentSerialNumberAndFamilyRelationshipCode(number,"모").orElseThrow(NotFoundResidentException::new);
    }
}

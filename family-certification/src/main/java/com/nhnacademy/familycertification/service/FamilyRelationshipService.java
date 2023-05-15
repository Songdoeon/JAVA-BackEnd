package com.nhnacademy.familycertification.service;


import com.nhnacademy.familycertification.domain.FamilyRelationshipDTO;
import com.nhnacademy.familycertification.domain.FamilyRelationshipReportDTO;
import com.nhnacademy.familycertification.entity.FamilyRelationship;
import com.nhnacademy.familycertification.repository.FamilyRelationshipRepository;
import com.nhnacademy.familycertification.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public List<FamilyRelationshipReportDTO> getFamilyRelationNumberAndCode(Long serialNumber) {
        List<FamilyRelationshipDTO> familyRelationshipDTOList = familyRelationshipRepository.findByPkFamilyResidentSerialNumber(serialNumber);
        List<FamilyRelationshipReportDTO> result = new ArrayList<>();

        familyRelationshipDTOList.stream().forEach(familyRelationshipDTO -> {
            result.add(
                    new FamilyRelationshipReportDTO(
                            familyRelationshipDTO.getFamilyRelationshipCode(),
                            residentRepository.findById(familyRelationshipDTO.getFamilySerialNumber()).get()
                    )
            );
        });
        return result;
    }
}

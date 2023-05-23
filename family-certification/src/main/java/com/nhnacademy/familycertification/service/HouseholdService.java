package com.nhnacademy.familycertification.service;

import com.nhnacademy.familycertification.domain.HouseholdDTO;
import com.nhnacademy.familycertification.entity.Household;
import com.nhnacademy.familycertification.entity.Resident;
import com.nhnacademy.familycertification.exception.NotFoundResidentException;
import com.nhnacademy.familycertification.repository.HouseholdRepository;
import com.nhnacademy.familycertification.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseholdService {

    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdDTO registerHousehold(HouseholdDTO householdDTO){

        Resident resident = residentRepository.findById(householdDTO.getHouseholdResidentSerialNumber()).orElseThrow(NotFoundResidentException::new);
        Household household = new Household().builder()
                .householdCompositionDate(householdDTO.getHouseholdCompositionDate())
                .householdResidentSerialNumber(resident)
                .householdCompositionReasonCode(householdDTO.getHouseholdCompositionReasonCode())
                .currentHouseMovementAddress(householdDTO.getCurrentHouseMovementAddress())
                .build();
        householdRepository.save(household);
        return householdDTO;
    }

    public void deleteHousehold(Long serialNumber){
        Household household = householdRepository.findByHouseholdResidentSerialNumber_ResidentSerialNumber(serialNumber);
        householdRepository.delete(household);
    }

    public Household findByResidentNumber(Long number){
        return householdRepository.findByHouseholdResidentSerialNumber_ResidentSerialNumber(number);
    }

}

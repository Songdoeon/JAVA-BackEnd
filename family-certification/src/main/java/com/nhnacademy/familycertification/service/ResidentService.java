package com.nhnacademy.familycertification.service;

import com.nhnacademy.familycertification.domain.ResidentRegisterDTO;
import com.nhnacademy.familycertification.entity.Resident;
import com.nhnacademy.familycertification.repository.ResidentRepository;
import com.nhnacademy.familycertification.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ResidentService {
    private final ResidentRepository residentRepository;

    public Resident register(ResidentRegisterDTO residentRegisterDTO){

        Resident resident = new Resident().builder()
                .name(residentRegisterDTO.getName())
                .residentRegistrationNumber(residentRegisterDTO.getResidentRegistrationNumber())
                .birthDate(residentRegisterDTO.getBirthDate())
                .birthPlaceCode(residentRegisterDTO.getBirthPlaceCode())
                .registrationBaseAddress(residentRegisterDTO.getRegistrationBaseAddress())
                .deathPlaceCode(residentRegisterDTO.getDeathPlaceCode())
                .deathDate(residentRegisterDTO.getDeathDate())
                .genderCode(residentRegisterDTO.getGenderCode())
                .deathPlaceAddress(residentRegisterDTO.getDeathPlaceAddress())
                .build();

        return residentRepository.saveAndFlush(resident);
    }
    public Page<Resident> findAll(Pageable pageable) {
        return residentRepository.findAll(pageable);
    }

    public Resident findBySerialId(Long serialNumber) {
        return residentRepository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);
    }


//    public Resident findByResidentId(String residentId) {
//        return residentRepository.findByResidentId(residentId).orElseThrow(NotFoundResidentException::new);
//    }
}

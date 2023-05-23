package com.nhnacademy.familycertification.service;

import com.nhnacademy.familycertification.domain.ResidentModifyDTO;
import com.nhnacademy.familycertification.domain.ResidentRegisterDTO;
import com.nhnacademy.familycertification.entity.Resident;
import com.nhnacademy.familycertification.repository.ResidentRepository;
import com.nhnacademy.familycertification.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentRegisterDTO register(ResidentRegisterDTO residentRegisterDTO){

        Resident resident = new Resident().builder()
                .name(residentRegisterDTO.getName())
                .residentRegistrationNumber(residentRegisterDTO.getResidentRegistrationNumber())
                .residentId(residentRegisterDTO.getResidentId())
                .password(passwordEncoder.encode(residentRegisterDTO.getPassword()))
                .birthDate(residentRegisterDTO.getBirthDate())
                .birthPlaceCode(residentRegisterDTO.getBirthPlaceCode())
                .registrationBaseAddress(residentRegisterDTO.getRegistrationBaseAddress())
                .deathPlaceCode(residentRegisterDTO.getDeathPlaceCode())
                .deathDate(residentRegisterDTO.getDeathDate())
                .genderCode(residentRegisterDTO.getGenderCode())
                .deathPlaceAddress(residentRegisterDTO.getDeathPlaceAddress())
                .build();
        residentRepository.saveAndFlush(resident);
        return residentRegisterDTO;
    }
    public ResidentModifyDTO update(Long serialNumber, ResidentModifyDTO residentRegisterDTO){

        Resident resident = residentRepository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);
        resident.modifyResidentInfo(
                residentRegisterDTO.getName(),
                residentRegisterDTO.getRegistrationBaseAddress()
        );
        residentRepository.saveAndFlush(resident);
        return residentRegisterDTO;
    }
    public Resident findBySerialId(Long serialNumber) {
        return residentRepository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);
    }
    public Resident findByResidentId(String id){
        return residentRepository.findByResidentId(id).orElseThrow(NotFoundResidentException::new);
    }


}

package com.nhnacademy.familycertification.service;

import com.nhnacademy.familycertification.domain.birthdeath.BirthReportDTO;
import com.nhnacademy.familycertification.domain.birthdeath.BirthReportUpdateDTO;
import com.nhnacademy.familycertification.domain.birthdeath.DeathReportDTO;
import com.nhnacademy.familycertification.domain.birthdeath.DeathReportUpdateDTO;
import com.nhnacademy.familycertification.entity.BirthDeathReportResident;
import com.nhnacademy.familycertification.entity.Resident;
import com.nhnacademy.familycertification.exception.NotFoundResidentException;
import com.nhnacademy.familycertification.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.familycertification.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

@RequiredArgsConstructor
@Transactional
@Service
public class BirthDeathReportResidentService {
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    public BirthReportDTO registerBirth(Long serialNumber, BirthReportDTO birthReportDTO){

        Resident targetResident = residentRepository.findById(birthReportDTO.getResident()).orElseThrow(NotFoundResidentException::new);
        Resident reporter = residentRepository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);
        BirthDeathReportResident birthReport = BirthDeathReportResident.builder()
                .pk(new BirthDeathReportResident.Pk(birthReportDTO.getResident(),serialNumber,birthReportDTO.getBirthDeathTypeCode()))
                .birthDeathReportDate(birthReportDTO.getBirthDeathReportDate())
                .resident(targetResident)
                .reportResidentSerialNumber(reporter)
                .birthReportQualificationsCode(birthReportDTO.getBirthReportQualificationsCode())
                .emailAddress(birthReportDTO.getEmailAddress())
                .phoneNumber(birthReportDTO.getPhoneNumber())
                .build();
        birthDeathReportResidentRepository.save(birthReport);
        return birthReportDTO;
    }
    public BirthReportUpdateDTO updateBirth(Long serialNumber, Long targetNumber, BirthReportUpdateDTO birthReportDTO){

        BirthDeathReportResident birthReport = birthDeathReportResidentRepository.getBirthDeathReportResident(serialNumber,targetNumber)
                .orElseThrow(NotFoundResidentException::new);
        birthReport.setBirthDeathReportDate(birthReportDTO.getBirthDeathReportDate());
        birthReport.setPhoneNumber(birthReportDTO.getPhoneNumber());
        birthReport.setEmailAddress(birthReportDTO.getEmailAddress());

        birthDeathReportResidentRepository.saveAndFlush(birthReport);
        return birthReportDTO;
    }
    public void deleteBirth(Long number,Long targetNumber){
        BirthDeathReportResident birthReport = birthDeathReportResidentRepository.getBirthDeathReportResident(number,targetNumber).orElseThrow(NotFoundResidentException::new);
        birthDeathReportResidentRepository.delete(birthReport);
    }

    public DeathReportDTO registerDeath(Long serialNumber, DeathReportDTO deathReportDTO){
        Resident targetResident = residentRepository.findById(deathReportDTO.getResident()).orElseThrow(NotFoundResidentException::new);
        Resident reporter = residentRepository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);
        BirthDeathReportResident birthReport = BirthDeathReportResident.builder()
                .pk(new BirthDeathReportResident.Pk(deathReportDTO.getResident(),serialNumber,deathReportDTO.getBirthDeathTypeCode()))
                .birthDeathReportDate(deathReportDTO.getBirthDeathReportDate())
                .resident(targetResident)
                .reportResidentSerialNumber(reporter)
                .birthReportQualificationsCode(deathReportDTO.getDeathReportQualificationsCode())
                .emailAddress(deathReportDTO.getEmailAddress())
                .phoneNumber(deathReportDTO.getPhoneNumber())
                .build();
        birthDeathReportResidentRepository.save(birthReport);
        return deathReportDTO;
    }
    public DeathReportUpdateDTO updateDeath(Long serialNumber, Long targetNumber, DeathReportUpdateDTO deathReportUpdateDTO){

        BirthDeathReportResident birthReport = birthDeathReportResidentRepository.getBirthDeathReportResident(serialNumber,targetNumber)
                .orElseThrow(NotFoundResidentException::new);
        birthReport.setBirthDeathReportDate(deathReportUpdateDTO.getBirthDeathReportDate());
        birthReport.setPhoneNumber(deathReportUpdateDTO.getPhoneNumber());
        birthReport.setEmailAddress(deathReportUpdateDTO.getEmailAddress());

        birthDeathReportResidentRepository.saveAndFlush(birthReport);
        return deathReportUpdateDTO;
    }
    public void deleteDeath(Long number,Long targetNumber){
        BirthDeathReportResident deathReport = birthDeathReportResidentRepository.getBirthDeathReportResident(number,targetNumber).orElseThrow(NotFoundResidentException::new);
        birthDeathReportResidentRepository.delete(deathReport);
    }

    public BirthDeathReportResident findBirth(Long id){
        return birthDeathReportResidentRepository.findByResident_ResidentSerialNumberAndPk_BirthDeathTypeCode(id,"출생");
    }
    public BirthDeathReportResident findDeath(Long id){
        return birthDeathReportResidentRepository.findByResident_ResidentSerialNumberAndPk_BirthDeathTypeCode(id,"사망");
    }



}


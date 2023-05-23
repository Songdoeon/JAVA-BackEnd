package com.nhnacademy.familycertification.service;

import com.nhnacademy.familycertification.domain.HouseholdMovementAddressDTO;
import com.nhnacademy.familycertification.domain.HouseholdMovementAddressUpdateDTO;
import com.nhnacademy.familycertification.entity.Household;
import com.nhnacademy.familycertification.entity.HouseholdMovementAddress;
import com.nhnacademy.familycertification.exception.NotFoundResidentException;
import com.nhnacademy.familycertification.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.familycertification.repository.HouseholdRepository;
import com.nhnacademy.familycertification.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class HouseholdMovementService {

    private final HouseholdMovementAddressRepository repository;
    private final HouseholdRepository householdRepository;

    public HouseholdMovementAddressDTO registerMovement(HouseholdMovementAddressDTO DTO,Long number){
        Household household = householdRepository.findById(number).orElseThrow(NotFoundResidentException::new);

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress()
                .builder()
                .pk(new HouseholdMovementAddress.Pk(number,DTO.getHouseMovementReportDate()))
                .houseMovementAddress(DTO.getHouseMovementAddress())
                .household(household)
                .lastAddressYn(DTO.getLastAddressYn())
                .build();
        repository.save(householdMovementAddress);
        return DTO;
    }

    public HouseholdMovementAddressUpdateDTO updateMovement(HouseholdMovementAddressUpdateDTO DTO,String date,Long number){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date1 = LocalDate.parse(date,formatter);

        HouseholdMovementAddress householdMovementAddress = repository.findByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(date1,number).orElseThrow(NotFoundResidentException::new);

        householdMovementAddress.setHouseMovementAddress(DTO.getHouseMovementAddress());
        return DTO;
    }
    public void deleteMovement(Long number,String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date1 = LocalDate.parse(date,formatter);

        HouseholdMovementAddress householdMovementAddress = repository.findByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(date1,number).orElseThrow(NotFoundResidentException::new);
        repository.delete(householdMovementAddress);
    }

    public List<HouseholdMovementAddress> getList(Long number){
        return repository.findByPk_householdSerialNumber(number)
                .orElseThrow(NotFoundResidentException::new);
    }
}

package com.nhnacademy.familycertification.service;

import com.nhnacademy.familycertification.domain.HouseholdCompositionDTO;
import com.nhnacademy.familycertification.entity.Household;
import com.nhnacademy.familycertification.entity.HouseholdCompositionResident;
import com.nhnacademy.familycertification.entity.Resident;
import com.nhnacademy.familycertification.exception.NotFoundResidentException;
import com.nhnacademy.familycertification.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.familycertification.repository.HouseholdRepository;
import com.nhnacademy.familycertification.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseholdCompositionService {

    private final HouseholdCompositionResidentRepository repository;

    public List<HouseholdCompositionResident> getComposition(Long householdNum){
        List<HouseholdCompositionResident> list = repository.getComposition(householdNum)
                .orElseThrow(NotFoundResidentException::new);
        return list;
    }

}

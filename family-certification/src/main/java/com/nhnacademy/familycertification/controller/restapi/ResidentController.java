package com.nhnacademy.familycertification.controller.restapi;

import com.nhnacademy.familycertification.domain.FamilyRelationshipDTO;
import com.nhnacademy.familycertification.domain.ResidentRegisterDTO;
import com.nhnacademy.familycertification.entity.Resident;
import com.nhnacademy.familycertification.repository.FamilyRelationshipRepository;
import com.nhnacademy.familycertification.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService residentService;
    private final FamilyRelationshipRepository familyRelationshipRepository;

    @PostMapping
    public Resident registerResident(@RequestBody ResidentRegisterDTO residentRegisterDTO) {
        return residentService.register(residentRegisterDTO);
    }

    @PostMapping("/{serialNumber}/relationship")
    public Resident registerFamily(@RequestBody FamilyRelationshipDTO familyRelationshipDTO) {
        return familyRelationshipRepository.(familyRelationshipDTO);
    }
    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public Resident registerRelationship(@RequestBody FamilyRelationshipDTO familyRelationshipDTO) {

        return familyRelationshipRepository.register(familyRelationshipDTO);
    }
//    @PutMapping("/{serialNumber}")
//    public Resident modifyResident(@PathVariable(name = "serialNumber") Long serialNumber,
//                                   @RequestBody ResidentModifyDTO residentModifyDTO) {
//        return residentService.modifyResident(serialNumber, residentModifyDTO);
//    }
}

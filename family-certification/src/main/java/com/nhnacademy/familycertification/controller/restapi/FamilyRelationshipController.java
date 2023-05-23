package com.nhnacademy.familycertification.controller.restapi;

import com.nhnacademy.familycertification.domain.familyrelationship.FamilyRelationshipDTO;
import com.nhnacademy.familycertification.domain.familyrelationship.FamilyRelationshipUpdateDTO;
import com.nhnacademy.familycertification.entity.FamilyRelationship;
import com.nhnacademy.familycertification.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents/{serialNumber}")
public class FamilyRelationshipController {

    private final FamilyRelationshipService familyRelationshipService;
    @PostMapping("/relationship")
    public ResponseEntity<FamilyRelationshipDTO> registerFamily(@PathVariable(name="serialNumber") Long serialNumber, @RequestBody FamilyRelationshipDTO familyRelationshipDTO) {
        FamilyRelationshipDTO response = familyRelationshipService.registerFamilyRelationship(familyRelationshipDTO,serialNumber);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationshipUpdateDTO> updateFamily(
                                            @PathVariable(name="serialNumber") Long serialNumber,
                                           @PathVariable(name="familySerialNumber") Long familySerialNumber,
                                           @RequestBody FamilyRelationshipUpdateDTO familyRelationshipUpdateDTO) {

        FamilyRelationshipUpdateDTO response = familyRelationshipService.updateFamilyRelationship(familyRelationshipUpdateDTO,serialNumber,familySerialNumber);
        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("/relationship/{familySerialNumber}")
    public void deleteFamily(@PathVariable(name="serialNumber") Long serialNumber,
                             @PathVariable(name="familySerialNumber") Long familySerialNumber){
        familyRelationshipService.deleteFamilyRelationship(serialNumber,familySerialNumber);
    }


}

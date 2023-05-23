package com.nhnacademy.familycertification.controller.restapi;

import com.nhnacademy.familycertification.domain.HouseholdMovementAddressDTO;
import com.nhnacademy.familycertification.domain.HouseholdMovementAddressUpdateDTO;
import com.nhnacademy.familycertification.service.HouseholdMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HouseholdMovementController {

    private final HouseholdMovementService householdService;
    @PostMapping("/household/{householdSerialNumber}/movement")
    public ResponseEntity<HouseholdMovementAddressDTO> registerHousehold(
            @PathVariable(name="householdSerialNumber") Long number,
            @RequestBody HouseholdMovementAddressDTO DTO){
        HouseholdMovementAddressDTO res = householdService.registerMovement(DTO,number);

        return ResponseEntity.ok(res);
    }
    @PutMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<HouseholdMovementAddressUpdateDTO> updateHouseholdComposition(
            @PathVariable(name = "reportDate") String date,
            @PathVariable(name = "householdSerialNumber") Long number,
            @RequestBody HouseholdMovementAddressUpdateDTO DTO){
        HouseholdMovementAddressUpdateDTO res = householdService.updateMovement(DTO,date,number);

        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public void deleteHouseholdComposition(
            @PathVariable(name = "reportDate") String date,
            @PathVariable(name = "householdSerialNumber")Long serialNumber){
        householdService.deleteMovement(serialNumber,date);
    }
}

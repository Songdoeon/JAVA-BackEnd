package com.nhnacademy.familycertification.controller.restapi;

import com.nhnacademy.familycertification.domain.HouseholdDTO;
import com.nhnacademy.familycertification.service.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HouseholdController {

    private final HouseholdService householdService;
    @PostMapping("/household")
    public ResponseEntity<HouseholdDTO> registerHousehold(@RequestBody HouseholdDTO householdDTO){
        HouseholdDTO res = householdService.registerHousehold(householdDTO);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/household/{householdSerialNumber}")
    public void deleteHousehold(@PathVariable(name = "householdSerialNumber")Long serialNumber){
        householdService.deleteHousehold(serialNumber);
    }
}

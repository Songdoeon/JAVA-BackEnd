package com.nhnacademy.familycertification.controller.restapi;

import com.nhnacademy.familycertification.domain.birthdeath.BirthReportDTO;
import com.nhnacademy.familycertification.domain.birthdeath.BirthReportUpdateDTO;
import com.nhnacademy.familycertification.domain.birthdeath.DeathReportDTO;
import com.nhnacademy.familycertification.domain.birthdeath.DeathReportUpdateDTO;
import com.nhnacademy.familycertification.service.BirthDeathReportResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class BirthDeathReportController {

    private final BirthDeathReportResidentService service;

    @PostMapping("/{serialNumber}/birth")
    public ResponseEntity<BirthReportDTO> registerBirth(@PathVariable(name = "serialNumber")Long number,@RequestBody BirthReportDTO birthReportDTO){
        BirthReportDTO response = service.registerBirth(number,birthReportDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<BirthReportUpdateDTO> registerBirth(
            @PathVariable(name = "serialNumber")Long number,
            @PathVariable(name = "targetSerialNumber")Long targetNumber,
            @RequestBody BirthReportUpdateDTO birthReportDTO){
        BirthReportUpdateDTO response = service.updateBirth(number,targetNumber,birthReportDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public void deleteBirth(@PathVariable(name = "serialNumber")Long number, @PathVariable(name = "targetSerialNumber")Long targetNumber){
        service.deleteBirth(number,targetNumber);
    }



    @PostMapping("/{serialNumber}/death")
    public ResponseEntity<DeathReportDTO> registerDeath(@PathVariable(name = "serialNumber")Long number,@RequestBody DeathReportDTO deathReportDTO){
        DeathReportDTO response = service.registerDeath(number,deathReportDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<DeathReportUpdateDTO> registerDeath(
            @PathVariable(name = "serialNumber")Long number,
            @PathVariable(name = "targetSerialNumber")Long targetNumber,
            @RequestBody DeathReportUpdateDTO deathReportDTO){
        DeathReportUpdateDTO response = service.updateDeath(number,targetNumber,deathReportDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{serialNumber}/death/{targetSerialNumber}")
    public void deleteDeath(@PathVariable(name = "serialNumber")Long number, @PathVariable(name = "targetSerialNumber")Long targetNumber){
        service.deleteDeath(number,targetNumber);
    }
}

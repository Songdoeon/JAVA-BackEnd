package com.nhnacademy.familycertification.controller.restapi;

import com.nhnacademy.familycertification.domain.ResidentModifyDTO;
import com.nhnacademy.familycertification.domain.ResidentRegisterDTO;
import com.nhnacademy.familycertification.entity.BirthDeathReportResident;
import com.nhnacademy.familycertification.entity.Resident;
import com.nhnacademy.familycertification.service.BirthDeathReportResidentService;
import com.nhnacademy.familycertification.service.ResidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService residentService;
    @PostMapping
    public ResponseEntity<ResidentRegisterDTO> registerResident(@RequestBody ResidentRegisterDTO residentRegisterDTO) {
        ResidentRegisterDTO res = residentService.register(residentRegisterDTO);

        return ResponseEntity.ok(res);
    }
    @PutMapping("{serialNumber}")
    public ResponseEntity<ResidentModifyDTO> updateResident(@PathVariable(name = "serialNumber")Long serialNumber,@RequestBody ResidentModifyDTO residentModifyDTO) {
        ResidentModifyDTO res = residentService.update(serialNumber,residentModifyDTO);

        return ResponseEntity.ok(res);
    }

}

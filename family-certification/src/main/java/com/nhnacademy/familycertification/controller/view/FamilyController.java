package com.nhnacademy.familycertification.controller.view;

import com.nhnacademy.familycertification.domain.CertificateIssueDTO;
import com.nhnacademy.familycertification.service.CertificateIssueService;
import com.nhnacademy.familycertification.service.FamilyRelationshipService;
import com.nhnacademy.familycertification.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/family")
public class FamilyController {

    private final FamilyRelationshipService familyRelationshipService;
    private final CertificateIssueService certificateIssueService;
    private final ResidentService residentService;

    @GetMapping("/{serialNumber}")
    public String getFamilyRelationship(@PathVariable(name = "serialNumber") Long serialNumber,
                                        Model model) {

        CertificateIssueDTO certificateInfo = certificateIssueService.getCertificateInfoByResidentSerialNumber(serialNumber, "가족관계증명서");

        model.addAttribute("certificateInfo", certificateInfo);
        model.addAttribute("resident", residentService.findBySerialId(serialNumber));
        model.addAttribute("familyList", familyRelationshipService.getFamilyRelationNumberAndCode(serialNumber));

        return "familyRelationship";
    }
}

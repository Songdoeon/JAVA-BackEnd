package com.nhnacademy.familycertification.controller.view;

import com.nhnacademy.familycertification.domain.CertificateIssueDTO;
import com.nhnacademy.familycertification.entity.*;
import com.nhnacademy.familycertification.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyRelationshipService familyRelationshipService;
    private final CertificateIssueService certificateIssueService;
    private final HouseholdService householdService;
    private final HouseholdCompositionService householdCompositionService;
    private final ResidentService residentService;
    private final HouseholdMovementService householdMovementService;
    private final BirthDeathReportResidentService birthDeathReportResidentService;

    @GetMapping("/index")
    public String index(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User)authentication.getPrincipal();
        String id = user.getUsername();
        Resident resident = residentService.findByResidentId(id);
        Long serialNumber = resident.getResidentSerialNumber();

        model.addAttribute("resident",serialNumber);
        model.addAttribute("birth",birthDeathReportResidentService.findBirth(serialNumber));
        model.addAttribute("death",birthDeathReportResidentService.findDeath(serialNumber));
        return "index";
    }
    @GetMapping("/oauth")
    public String getOauth(){

        return "oauth";
    }
    @PostMapping("/oauth")
    public String oauth(@RequestParam(name="id")Long id, Model model){

        model.addAttribute("resident",id);
        model.addAttribute("birth",birthDeathReportResidentService.findBirth(id));
        model.addAttribute("death",birthDeathReportResidentService.findDeath(id));
        return "index";
    }

    @GetMapping("/family/{serialNumber}")
    public String getFamilyRelationship(@PathVariable(name = "serialNumber") Long serialNumber,
                                        Model model) {

        CertificateIssueDTO certificateInfo = certificateIssueService.getCertificateInfoByResidentSerialNumber(serialNumber, "가족관계증명서");

        model.addAttribute("certificateInfo", certificateInfo);
        model.addAttribute("resident", residentService.findBySerialId(serialNumber));
        model.addAttribute("familyList", familyRelationshipService.getFamilyRelationship(serialNumber));
        return "familyRelationship";
    }
    @GetMapping("/page/{serialNumber}")
    public String getMyPage(@PathVariable(name = "serialNumber") Long serialNumber,
                                        Model model) {

        CertificateIssueDTO certificateInfo = certificateIssueService.getCertificateInfoByResidentSerialNumber(serialNumber, "주민등록등본");
        Household household = householdService.findByResidentNumber(serialNumber);
        List<HouseholdMovementAddress> addressList = householdMovementService.getList(household.getHouseholdSerialNumber());
        List<HouseholdCompositionResident> familyList = householdCompositionService.getComposition(household.getHouseholdSerialNumber());
        model.addAttribute("household",household);
        model.addAttribute("certificateInfo", certificateInfo);
        model.addAttribute("addressList",addressList);
        model.addAttribute("familyList",familyList);
        return "myPage";
    }

    @GetMapping("/birth/{serialNumber}")
    public String birth(@PathVariable(name = "serialNumber") Long serialNumber,
                        Model model){
        BirthDeathReportResident report = birthDeathReportResidentService.findBirth(serialNumber);
        Resident resident = residentService.findBySerialId(serialNumber);
        model.addAttribute("report",report);
        model.addAttribute("date",resident.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")));
        model.addAttribute("resident",residentService.findBySerialId(serialNumber));
        model.addAttribute("father",familyRelationshipService.getFather(serialNumber));
        model.addAttribute("mother",familyRelationshipService.getMother(serialNumber));
        return "birth";
    }
        @GetMapping("/death/{serialNumber}")
        public String death(@PathVariable(name = "serialNumber") Long serialNumber,
                            Model model){
        BirthDeathReportResident report = birthDeathReportResidentService.findDeath(serialNumber);
        Resident resident = residentService.findBySerialId(serialNumber);
        model.addAttribute("report",report);
        model.addAttribute("date",resident.getDeathDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")));
        model.addAttribute("resident",resident);
        return "death";
    }
    @GetMapping("/list")
    public String list(@RequestParam(name = "num") Long serialNumber,
                       @RequestParam(name = "page") int pages,
                       @RequestParam(name = "size") int size,
                        Model model){
        Pageable pageable = PageRequest.of(pages,size);
        Page page = certificateIssueService.paging(serialNumber,pageable);
        List<CertificateIssue> list = page.getContent();

        model.addAttribute("num",serialNumber);
        model.addAttribute("list",list);
        model.addAttribute("size",page.getTotalPages()-1);
        model.addAttribute("page",pageable.getPageNumber());
        return "list";
    }
}

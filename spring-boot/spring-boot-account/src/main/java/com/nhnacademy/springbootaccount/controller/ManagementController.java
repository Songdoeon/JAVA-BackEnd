package com.nhnacademy.springbootaccount.controller;

import com.nhnacademy.springbootaccount.actuator.MyHealthIndicator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagementController {

    private final MyHealthIndicator myHealthIndicator;

    @PostMapping("/management/fail")
    public String fail(){
        myHealthIndicator.healthFail();
        return "health fail ㅠㅠ";
    }

    @PostMapping("/management/recover")
    public String recover(){
        myHealthIndicator.recover();
        return "health up!";
    }
}

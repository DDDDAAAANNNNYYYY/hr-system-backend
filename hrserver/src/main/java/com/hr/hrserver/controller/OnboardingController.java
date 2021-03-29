package com.hr.hrserver.controller;

import com.hr.hrserver.model.OnboardingForm;
import com.hr.hrserver.service.onBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OnboardingController {
    @Autowired
    onBoardingService OBservice;

    @PostMapping("/saveOnboardingForm")
    public String onboard(@RequestBody OnboardingForm form) {
        OBservice.saveOnboardingData(form);
        System.out.println(form);
        return "sucess";
    }

}

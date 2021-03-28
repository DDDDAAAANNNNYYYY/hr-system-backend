package com.hr.hrserver.controller;

import com.hr.hrserver.pojo.VisaStatus;
import com.hr.hrserver.service.VisaService;
import com.hr.hrserver.service.emailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisaController {
    VisaService visaService = new VisaService();
    emailService eservice = new emailService();
    @PostMapping("/getVisaStatus")
    public String getVisaStatus(String name){
        VisaStatus visatyep = visaService.getVisaStatusByName(name);
        String type = visatyep.getVisaType();
        return type;
//        return null;
    }

    @PostMapping("/changeVisaStatus")
    public String changeVisaStatus(String newStatus, String userName) {
        VisaStatus vs = visaService.getVisaStatusByName(userName);
        vs.setVisaType(newStatus);
        visaService.updateVisaStatud(vs);
        String email = "registerhr2021@gmail.com";
        String text = "Employee: "+ userName + "'s status has changed to:"+ newStatus+ ". please processing";
        eservice.sendEmail(email,text);
        return "change sucessful";
    }
}

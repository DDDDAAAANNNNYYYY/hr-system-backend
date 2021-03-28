package com.hr.hrserver.controller;

import com.hr.hrserver.model.VisaChange;
import com.hr.hrserver.pojo.VisaStatus;
import com.hr.hrserver.service.VisaService;
import com.hr.hrserver.service.emailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisaController {
    VisaService visaService = new VisaService();
    @Autowired
    emailService eservice = new emailService();
    @PostMapping("/getVisaStatus")
    public String getVisaStatus(@RequestBody String name){
        VisaStatus visatyep = visaService.getVisaStatusByName(name);
        String type = visatyep.getVisaType();
        System.out.println("get here visa");
        System.out.println(type);
        return type;
//        return null;
    }

    @PostMapping("/changeVisaStatus")
    public String changeVisaStatus(@RequestBody VisaChange vc) {
        VisaChange v = (VisaChange) vc;

        VisaStatus vs = visaService.getVisaStatusByName(vc.getUsername());
        vs.setVisaType(vc.getChangeStatus());
        visaService.updateVisaStatud(vs);
        String email = "registerhr2021@gmail.com";
        System.out.println(eservice);
        String text = "Employee: "+ vc.getUsername() + "'s status has changed to:"+ vc.getChangeStatus()+ ". please processing";
        eservice.sendNotify(email,text);
        System.out.println("email sent");
        return "change sucessful";
    }
//      @PostMapping("/changeVisaStatus")
//public VisaChange changeVisaStatus(@RequestBody VisaChange vc) {
//    System.out.println(vc.toString());
//    return vc;
//}
}

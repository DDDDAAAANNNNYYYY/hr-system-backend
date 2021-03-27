package com.hr.hrserver.controller;

import com.hr.hrserver.dao.VisaStatusDao;
import com.hr.hrserver.pojo.VisaStatus;
import com.hr.hrserver.service.emailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisaController {
    VisaStatusDao visaStatusDao = new VisaStatusDao();
    emailService eservice = new emailService();
    @PostMapping("/getVisaStatus")
    public String getVisaStatus(String name){
        VisaStatus visatyep = visaStatusDao.getVisaStatusByUsername(name);
        String type = visatyep.getVisaType();
        return type;
    }

    @PostMapping("/changeVisaStatus")
    public String changeVisaStatus(String newStatus, String userName) {
        VisaStatus vs = visaStatusDao.getVisaStatusByUsername(userName);
        vs.setVisaType(newStatus);
        visaStatusDao.saveOrupdate(vs);
        String email = "registerhr2021@gmail.com";
        String text = "Employee: "+ userName + "'s status has changed to:"+ newStatus+ ". please processing";
        eservice.sendEmail(email,text);
        return "change sucessful";
    }
}

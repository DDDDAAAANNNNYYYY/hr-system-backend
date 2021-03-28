package com.hr.auth.controller;

import com.hr.auth.db.PersonalInfo;
import com.hr.auth.service.PersonalInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalInfoController {
    @PostMapping("/personinfo")
    public PersonalInfo getPersonalInfo(@RequestBody String username) {
        PersonalInfoService pservice = new PersonalInfoService();
        PersonalInfo p = pservice.getPersonalInfobyUname(username);
        return p;
    }
}

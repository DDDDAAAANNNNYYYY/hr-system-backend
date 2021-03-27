package com.hr.auth.test;

import com.hr.auth.service.PersonalInfoService;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class dbtest {

    PersonalInfoService personalInfoService = new PersonalInfoService();
    @Test
    public void test() {
       personalInfoService.getPersonalInfobyEmail("admin@gmail.com");

    }

}

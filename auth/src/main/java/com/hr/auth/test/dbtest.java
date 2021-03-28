package com.hr.auth.test;

import com.hr.auth.db.PersonalInfo;
import com.hr.auth.service.PersonalInfoService;
import org.testng.annotations.Test;

public class dbtest {

    PersonalInfoService personalInfoService = new PersonalInfoService();
    @Test
    public void test() {
       PersonalInfo p = personalInfoService.getPersonalInfobyUname("Dany");
        System.out.println(p);

    }

}

package com.hr.hrserver.test;

import com.hr.hrserver.pojo.VisaStatus;
import com.hr.hrserver.service.VisaService;
import org.junit.Test;

public class test {

    VisaService vs= new VisaService();
    @Test
    public void test() {
        VisaStatus status = vs.getVisaStatusByName("dany");
        System.out.println(status);
    }

}

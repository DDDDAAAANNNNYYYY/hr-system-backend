package com.hr.hrserver.test;

import com.hr.hrserver.dao.EmployeeDaoImpl;
import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.model.OnboardingForm;
import com.hr.hrserver.pojo.HouseDetail;
import com.hr.hrserver.pojo.VisaStatus;
import com.hr.hrserver.service.HousingAndFacilityReportService;
import com.hr.hrserver.service.VisaService;
import com.hr.hrserver.service.onBoardingService;
import com.hr.hrserver.util.UtilFunction;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {

    VisaService vs= new VisaService();
    onBoardingService observice= new onBoardingService();
    @Test
    public void test2() {
        UserDaoImpl userDao = new UserDaoImpl();
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        int uid = userDao.findIdbyNmae("Dany");
        int eid = employeeDao.getEmployeeByUserId(uid).getID();
        System.out.println(eid);
    }
    @Test
    public void test1() {
        HousingAndFacilityReportService hs = new HousingAndFacilityReportService();
        HouseDetail h = hs.getHouseDetailByEmployeeID(1);
        System.out.println(h);
    }

    @Test
    public void test() {
        OnboardingForm form = new OnboardingForm();
        form.setFirstName("Stan");
        form.setLastName("Xuan");
        form.setMiddleName("B.");
        form.setPreferredName("Xuan");
        form.setAvatar("test/Danybili.jpg");
        form.setAddress("85 Brookline Street");
        form.setCellPhone("6175554444");
        form.setWorkPhone("3332221111");
        form.setSSN("853412609");
        form.setBirthday(UtilFunction.getTodayDate());
        form.setGender("Male");
        form.setIsCitizenOrPerm("True");
        form.setGreenCardOrCitizen("False");
        form.setWorkAuth("Other");
        form.setWorkAuthOther("UserInputString");
        form.setWorkAuthOtherStart("2010-02-04");
        form.setWorkAuthOtherEnd("2015-02-03");
        form.setWorkAuthFile("test/printPage2.pdf");
        form.setHasDriverLicense("abcdes12");
        form.setDriverLicenseNumber("123456");
        form.setDriverLicenseExpirationDate(UtilFunction.getTodayDate());

        form.setReferred("True");
        form.setReferFirstName("Dany");
        form.setReferLastName("Liu");
        form.setMiddleName("S.");
        form.setReferPhone("3216540987");
        form.setReferAddress("10 Milton road");
        form.setReferEmail("bbb@gmail.com");
        form.setReferRelationship("friend");

        Map<String, String> clist1 = new HashMap<>();
        clist1.put("emergencyContactFirstName","c1firstname");
        clist1.put("emergencyContactLastName","c1lastname");
        clist1.put("emergencyContactMiddleName","c1m");
        clist1.put("emergencyContactPhone","1111111111");
        clist1.put("emergencyContactEmail","c1email@mail.com");
        clist1.put("emergencyContactRelationship","r1");

        Map<String, String> clist2 = new HashMap<>();
        clist2.put("emergencyContactFirstName","c2firstname");
        clist2.put("emergencyContactLastName","c2lastname");
        clist2.put("emergencyContactMiddleName","c2m");
        clist2.put("emergencyContactPhone","2222222222");
        clist2.put("emergencyContactEmail","c2email@mail.com");
        clist2.put("emergencyContactRelationship","r2");

        List clist = Arrays.asList(clist1, clist2);;
        form.setEmergencyContacts(clist);
//        VisaStatus status = vs.getVisaStatusByName("dany");
        System.out.println(form);
        observice.saveOnboardingData(form);

    }

}

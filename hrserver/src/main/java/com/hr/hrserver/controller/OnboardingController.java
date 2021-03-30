package com.hr.hrserver.controller;

import com.hr.hrserver.dao.EmployeeDaoImpl;
import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.model.OnboardingForm;
import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.service.onBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OnboardingController {

    onBoardingService OBservice = new onBoardingService();
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();


    @PostMapping("/saveOnboardingForm")
    public String onboard(@RequestBody OnboardingForm form) {
        OBservice.saveOnboardingData(form);
        System.out.println(form);
        return "sucess";
    }
    @GetMapping("/getOnboardingForm")
    public List<OnboardingForm> getOnboard(){
        System.out.println("get here getOnbord");
        String status = "Inprogress";

        List<OnboardingForm> onboardingFormList = OBservice.getAllOnboardingInfoByStatus(status);

        System.out.println("my boarding form" + onboardingFormList);
        return onboardingFormList;
    }

    @PostMapping("/submitAvadar")
    public String saveAvatar(@RequestBody Map<String, String> urlInfo){
//        List<OnboardingForm> onboardingFormList = OBservice;
        String username = urlInfo.get("name");
        String url = urlInfo.get("url");
        int id = userDao.findIdbyNmae(username);
        Employee e = employeeDao.getEmployeeByUserId(id);
        if(e == null) {
            e = new Employee();
            e.setAvatar(url);
            employeeDao.save(e);
            return url;
        }
        e.setAvatar(url);
        employeeDao.saveOrupdate(e);


        return url;
    }


}

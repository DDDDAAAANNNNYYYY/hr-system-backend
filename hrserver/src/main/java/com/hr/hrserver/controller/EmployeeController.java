package com.hr.hrserver.controller;

import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.model.OnboardingForm;
import com.hr.hrserver.pojo.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@RestController
public class EmployeeController {

    @Autowired //WHY Autoware doesn't work
    UserDaoImpl userDao ;
    @CrossOrigin
    @RequestMapping ("/saveuser")
    public void saveuser(String username, String password) {
        System.out.println("frontend visited!");
//        User user = new User();
//        user.setId("999");
//        user.setUsername(username);
//        user.setPassword(password);
//        System.out.println(user);
//        userDao.save(user);
    }

    @CrossOrigin
    @RequestMapping ("/saveOnboardingForm")
    public OnboardingForm saveForm(@RequestBody OnboardingForm form) {
//        System.out.println(form.getAvatar() + "first name: " + form.getFirstName() + "emergencycontact: " + form.getEmergecyContacts());
        System.out.println(form.toString());
        return form;
    }
}

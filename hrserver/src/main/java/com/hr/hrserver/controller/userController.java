package com.hr.hrserver.controller;

import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.pojo.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RunWith(SpringRunner.class)
@SpringBootTest
@RestController
public class userController {
    @Autowired //WHY Autoware doesn't work
    UserDaoImpl userDao ;
    @CrossOrigin
    @RequestMapping("/saveuser")
    public void saveuser(String username, String password) {

//        User user = new User();
//        user.setId("888");
//        user.setUsername(username);
//        user.setPassword(password);
//        System.out.println(user);
//        userDao.save(user);
    }
}

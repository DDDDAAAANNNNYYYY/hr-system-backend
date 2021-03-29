package com.hr.hrserver.controller;

import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.model.UserInfo;
import com.hr.hrserver.pojo.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@RestController
public class userController {
    @Autowired //WHY Autoware doesn't work
    UserDaoImpl userDao ;
    @CrossOrigin
    @RequestMapping("/saveuser")
    public void saveuser(@RequestBody UserInfo userInfo) {
        User user = new User();
        user.setEmail(userInfo.getEmail());
        user.setUsername(userInfo.getUsername());
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");

        Date today = new Date();

        user.setCreateDate(sdf.format(today));
        user.setModifiedDate(sdf.format(today));
        userDao.save(user);

//        User user = new User();
//        user.setId("888");
//        user.setUsername(username);
//        user.setPassword(password);
//        System.out.println(user);
//        userDao.save(user);
    }
}

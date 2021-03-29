package com.hr.hrserver.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hr.hrserver.dao.registrationTokenDaoImpl;
import com.hr.hrserver.pojo.RegistrationToken;
import com.hr.hrserver.service.emailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@CrossOrigin
public class emailController {
    @Autowired
    private emailService eservice;
    private String tokenSecret = "my-secret-string";

    @RequestMapping("/sendEmail")
    @ResponseBody
    public String hrSendEmail(@RequestParam("email") String email) {
        System.out.println(email);
        String token = JWT.create().sign(Algorithm.HMAC256(tokenSecret));
        RegistrationToken rt = new RegistrationToken();
        rt.setEmail(email);
        rt.setToken(token);
        Date d = new Date();
        rt.setCreateBy(d);
        registrationTokenDaoImpl rd = new registrationTokenDaoImpl();
        rd.saveReigistrationToken(rt);

        //with https:// has insecure problem
        String text = "To register, please click : http://10.0.0.17:8081/email_validation?token="
                + token + "&email="+email;
        eservice.sendEmail(email, text);
        return "success";
    }
    @RequestMapping("/email_validation")
    public String toRegister(@RequestParam("token") String token, @RequestParam("email") String email) {
        //check if the token in database=> flag
        System.out.println(token);
        if(true) {
            return "redirect:http://localhost:4200/register?email=" + email;
        } else {
            return "error";
        }
    }
    @RequestMapping("/email_notification")
    public void sendNotification(String email) {
        String text = "";
        eservice.sendEmail(email, text );
    }

}

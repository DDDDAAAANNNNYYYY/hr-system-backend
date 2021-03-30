package com.hr.hrserver.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hr.hrserver.dao.registrationTokenDaoImpl;
import com.hr.hrserver.pojo.RegistrationToken;
import com.hr.hrserver.service.emailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
@CrossOrigin
public class emailController {
    @Autowired
    private emailService eservice;
    private String tokenSecret = "my-secret-string";
    @Autowired
    registrationTokenDaoImpl registrationTokenDao;

    @PostMapping("/sendEmail")
    @ResponseBody
    public String hrSendEmail(@RequestBody Map<String,String> info) {
        System.out.println(info);
        String email = info.get("email");
        System.out.println(email);

        tokenSecret = tokenSecret + (new Date()).toString();
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
        if(registrationTokenDao.isValidToken(token)) {
            return "redirect:http://localhost:4200/register?email=" + email + "&token="+token;
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

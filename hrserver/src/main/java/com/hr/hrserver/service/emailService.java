package com.hr.hrserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class emailService {
    @Autowired
    private MailSender mailSender;

//    @RequestMapping(value = "/sendEmail")

    public void sendEmail(String email, String text) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            //need to save RegistrationToken--to be implemented.
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Register Invitation");
            simpleMailMessage.setFrom("registerhr2021@gmail.com");
            simpleMailMessage.setText(text);
            mailSender.send(simpleMailMessage);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

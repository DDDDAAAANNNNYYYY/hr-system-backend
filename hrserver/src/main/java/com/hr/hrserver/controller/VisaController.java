package com.hr.hrserver.controller;

import com.hr.hrserver.dao.PersonalDocumentDaoImpl;
import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.model.VisaChange;
import com.hr.hrserver.pojo.PersonalDocument;
import com.hr.hrserver.pojo.VisaStatus;
import com.hr.hrserver.service.VisaService;
import com.hr.hrserver.service.emailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class VisaController {
    VisaService visaService = new VisaService();
    @Autowired
    emailService eservice = new emailService();
    @Autowired
    UserDaoImpl userDao;

    PersonalDocumentDaoImpl pdao = new PersonalDocumentDaoImpl();
    @PostMapping("/getVisaStatus")
    public String getVisaStatus(@RequestBody String name){
        VisaStatus visatyep = visaService.getVisaStatusByName(name);
        String type = visatyep.getVisaType();
        System.out.println("get here visa");
        System.out.println(type);
        return type;
//        return null;
    }
    @PostMapping("/getNotification")
    public String getNotification(@RequestBody String name) throws ParseException {
        System.out.println("visa notification here");
        VisaStatus visatyep = visaService.getVisaStatusByName(name);
        System.out.println("visa notification here b");
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String d1 = simpleFormat.format(d);
        Date d2 = simpleFormat.parse(d1);
        System.out.println("visa notification here c");
        long from1 = d2.getTime();
        long to1 = visatyep.getVisaEndDate().getTime();
        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
        System.out.println(days);

        String type = visatyep.getVisaType();
        if(type.equals("OPT Receipt")) {
            return "OPTEAD";
        } else if(type.equals("OPT STEM Receipt")) {
            return "OPTSTEMEAD";
        } else if(type.equals("OPT EAD") && days <= 100 ) {
            return "I983";
        } else {
            return "AllSet";
        }

    }

    @PostMapping("/changeVisaStatus")
    public String changeVisaStatus(@RequestBody VisaChange vc) {
        VisaChange v = (VisaChange) vc;

        VisaStatus vs = visaService.getVisaStatusByName(vc.getUsername());
        vs.setVisaType(vc.getChangeStatus());
        visaService.updateVisaStatud(vs);
        String email = "registerhr2021@gmail.com";
        System.out.println(eservice);
        String text = "Employee: "+ vc.getUsername() + "'s status has changed to:"+ vc.getChangeStatus()+ ". please processing";
        eservice.sendNotify(email,text);
        System.out.println("email sent");
        return "change sucessful";
    }
    @PostMapping("/submitDoc")
    public String submitDoc(@RequestBody Map<String, String> urlInfo) {
        System.out.println("submitDoc");
        String username = urlInfo.get("name");
        String url = urlInfo.get("url");
        String filetype = "I983";
        int id = userDao.findIdbyNmae(username);
        PersonalDocument pd = new PersonalDocument();
        System.out.println("url"+ url);
        pd.setEmployeeID(id);
        Date d = new Date();
        pd.setCreatedDate(d);
        pd.setPath(url);
        pd.setTitle(filetype);
        pd.setComment("waiting for processing");
        pdao.save(pd);


        System.out.println(url);
        System.out.println(filetype);
        return "change sucessful";
    }

//      @PostMapping("/changeVisaStatus")
//public VisaChange changeVisaStatus(@RequestBody VisaChange vc) {
//    System.out.println(vc.toString());
//    return vc;
//}
}

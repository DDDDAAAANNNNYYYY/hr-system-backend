package com.hr.auth.db;

import com.hr.hrserver.pojo.Contact;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonalInfo {
    String name;
    String preferredName;
    String DOB;
    String SSN;
    String email;
    String workphone;
    String cellphone;
    String primaryAddress;
    String secondaryAddress;
    List<Contact> contacts;
    //work authorizatioon
    String visaType;
    Date startDate;
    Date endDate;
    String title;
    String Gender;
    String isCitizenOrPerm;
    String workAuth;
    Date VisaStartDate;
    Date VisaEndDate;
    String Visatype;
    String empTitle;
//    List<Document> personalDocuments;
}

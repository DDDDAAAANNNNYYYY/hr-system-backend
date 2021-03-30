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
    String ssn;
    String email;
    String workPhone;
    String cellphone;
    String primaryAddress;
    String secondaryAddress;
    List<Contact> contacts;
    //work authorizatioon
    String visaType;
    String employmentStart;
    String employmentEnd;
    String title;
    String Gender;
    String isCitizenOrPerm;
    String WorkAuth;
    String workAuthOtherStart;
    Date workAuthOtherEnd;
    String Visatype;
    String employmentTitle;
    String avatar;
//    List<Document> personalDocuments;
}

package com.hr.auth.service;


import com.hr.auth.db.PersonalInfo;


import com.hr.hrserver.dao.*;
import com.hr.hrserver.pojo.Contact;
import com.hr.hrserver.pojo.Document;
import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.VisaStatus;
import com.hr.hrserver.test.ContactDaoAuthAuth;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class PersonalInfoService {

    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    ContactDaoImpl contactDao = new ContactDaoImpl();

    PersonalDocumentDaoImpl documentsDao = new PersonalDocumentDaoImpl();
    VisaStatusDao visaStatusDao = new VisaStatusDao();

    public PersonalInfo getPersonalInfobyUname(String username){
        Employee emp = employeeDao.getEmployeeByUserName(username);

        int id = emp.getID();
        List<Contact> contactList = contactDao.getAllContactByEmployeeID(id);

        List<Document> documents = documentsDao.getDocumentListbyEmployeeId(id);
        List<VisaStatus> vsList = visaStatusDao.getVisaById(id);
        VisaStatus vs = vsList.get(0);
        PersonalInfo p = new PersonalInfo();
        p.setName(emp.getFirstName() + " "+ emp.getLastName());
        p.setPreferredName(emp.getPreferredName());
        p.setDOB(emp.getDOB().toString());
        String address = emp.getAddress();
        String[] addr = address.split(",");
        p.setPrimaryAddress(addr[0]);
        p.setSecondaryAddress(addr[1]);
        p.setSsn(emp.getSSN());
        p.setGender(emp.getGender());
        p.setEmail(emp.getEmail());
        p.setCellphone(emp.getCellPhone());
        p.setWorkPhone(emp.getAlternatePhone());
        p.setIsCitizenOrPerm(""+emp.getIsCitizen());
        p.setEmploymentStart(emp.getStartDate().toString());
        p.setEmploymentEnd("abc"+emp.getEndDate().toString()+"abc");
        p.setEmploymentTitle(emp.getTitle());
        p.setWorkAuthOtherStart(vs.getVisaEndDate().toString());
        p.setWorkAuthOtherEnd(vs.getVisaStartDate());
        p.setContacts(contactList);
        p.setWorkAuth(vs.getVisaType());
        p.setAvatar(emp.getAvatar());
        System.out.println(vs);

        System.out.println(contactList);
        System.out.println(documents);
        return p;
    }
}

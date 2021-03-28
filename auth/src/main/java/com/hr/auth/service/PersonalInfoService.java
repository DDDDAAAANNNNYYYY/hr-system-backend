package com.hr.auth.service;

import com.hr.auth.dao.base.ContactDaoAuthAuth;
import com.hr.auth.dao.base.DocumentsDaoAuthAuth;
import com.hr.auth.dao.base.EmployeeDaoAuthImplAuth;
import com.hr.auth.dao.base.VisaStatusDao;
import com.hr.auth.db.PersonalInfo;


import com.hr.hrserver.pojo.Contact;
import com.hr.hrserver.pojo.Document;
import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.VisaStatus;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class PersonalInfoService {

    EmployeeDaoAuthImplAuth employeeDao = new EmployeeDaoAuthImplAuth();

    ContactDaoAuthAuth contactDao = new ContactDaoAuthAuth();

    DocumentsDaoAuthAuth documentsDaoAuthAuth = new DocumentsDaoAuthAuth();
    VisaStatusDao visaStatusDao = new VisaStatusDao();

    public PersonalInfo getPersonalInfobyUname(String username){
        Employee emp = employeeDao.getEmployeeByUserName(username);

        int id = emp.getID();
        List<Contact> contactList = contactDao.getContactListbyEmployeeId(id);

        List<Document> documents = documentsDaoAuthAuth.getDocumentListbyEmployeeId(id);
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
        p.setSSN(emp.getSSN());
        p.setGender(emp.getGender());
        p.setEmail(emp.getEmail());
        p.setCellphone(emp.getCellPhone());
        p.setWorkphone(emp.getAlternatePhone());
        p.setIsCitizenOrPerm(""+emp.getIsCitizen());
        p.setStartDate(emp.getStartDate());
        p.setEndDate(emp.getEndDate());
        p.setTitle(emp.getTitle());
        p.setVisaEndDate(vs.getVisaEndDate());
        p.setVisaStartDate(vs.getVisaStartDate());
        p.setContacts(contactList);
        System.out.println(vs);

        System.out.println(contactList);
        System.out.println(documents);
        return p;
    }
}

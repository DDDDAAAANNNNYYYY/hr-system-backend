//package com.hr.hrserver.test;
//
//import com.hr.hrserver.PersonalInfo;
//import com.hr.auth.pojo.Employee;
//import com.hr.hrserver.pojo.Contact;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Service;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Service
//public class PersonalInfoService {
//    @Autowired
//    EmployeeDaoAuthImplAuth employeeDao = new EmployeeDaoAuthImplAuth();
//    @Autowired
//    ContactDaoAuthAuth contactDao = new ContactDaoAuthAuth();
//    @Autowired
//    DocumentsDaoAuthAuth documentsDaoAuthAuth = new DocumentsDaoAuthAuth();
//    public PersonalInfo getPersonalInfobyEmail(String email){
//        Employee e = employeeDao.getEmployeeByUserEmail(email);
//        int id = e.getID();
//        List<Contact> contactList = contactDao.getContactListbyEmployeeId(id);
//
//        List<String> documents = documentsDaoAuthAuth.getDocumentListbyEmployeeId(id);
//        System.out.println(e);
//        System.out.println(contactList);
//        System.out.println(documents);
//        return null;
//    }
//}

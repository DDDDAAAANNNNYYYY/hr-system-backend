package com.hr.hrserver.service;


import com.hr.hrserver.dao.*;
import com.hr.hrserver.model.OnboardingForm;
import com.hr.hrserver.pojo.Contact;
import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.PersonalDocument;
import com.hr.hrserver.util.UtilFunction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class onBoardingService {

    public void saveOnboardingData(OnboardingForm onboardingInput){

        //save to table employee
        Employee employee = new Employee();

        //find userID by user name
        UserDaoImpl userDao = new UserDaoImpl();
        int userId = userDao.findIdbyEmail((String) onboardingInput.getEmail());

        employee.setFirstName((String) onboardingInput.getFirstName());
        employee.setLastName((String) onboardingInput.getLastName());
        employee.setMiddleName((String) onboardingInput.getMiddleName());
        employee.setPreferredName((String) onboardingInput.getPreferredName());

        //use default avatar
        employee.setAvatar("");
        //save avatar to s3
        //employee.setAvatar(onboardingInputForm.avatar);

        employee.setSSN((String) onboardingInput.getSSN());

        //parse date
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String sqlDate = sdf.format();
//        employee.setDOB(sqlDate);
        employee.setDOB((Date) onboardingInput.getBirthday());

        employee.setGender((String) onboardingInput.getGender());
        employee.setCellPhone((String) onboardingInput.getCellPhone());
        employee.setAlternatePhone((String) onboardingInput.getWorkPhone());
        employee.setEmail((String) onboardingInput.getEmail());
        employee.setAddress((String) onboardingInput.getAddress());

        employee.setStartDate(UtilFunction.getTodayDate());

//        employee.setCar((String) onboardingInput.getC("carBrand"));
//        employee.setCarModel((String) onboardingInput.get("carModel"));
//        employee.setCarColor((String) onboardingInput.get("carColor"));
        employee.setDriverLisence((String) onboardingInput.getDriverLicenseNumber());

//        parse date
//        employee.setDriverLisence_ExpirationDate(
//         (Date) onboardingInput.get("driverLicenseExpirationDate")
//         );
//        employee.setDOB((Date) onboardingInput.get("dob"));

        employee.setIsCitizen(onboardingInput.getIsCitizenOrPerm()=="yes"?1:0);

        EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
        int employeeID = (int) employeeDaoImpl.save(employee);

        //save to table contact
        ContactDaoImpl cdi = new ContactDaoImpl();

//        System.out.println(onboardingInput.getEmergencyContacts().get(0));

        List<Contact> mycontacts = new ArrayList<>();
        for(int i = 0; i < onboardingInput.getEmergencyContacts().size(); i++) {
            Contact c = new Contact();
            String name = "" +((HashMap) onboardingInput.getEmergencyContacts().get(i)).get("emergencyContactFirstName")+((HashMap) onboardingInput.getEmergencyContacts().get(i)).get("emergencyContactMiddleName") +((HashMap) onboardingInput.getEmergencyContacts().get(i)).get("emergencyContactLastName");
            c.setContactsName(name);
            String email = ""+((HashMap) onboardingInput.getEmergencyContacts().get(i)).get("emergencyContactEmail");
            c.setContactEmail(email);
            c.setEmployeeID(employeeID);
            c.setPhone(""+((HashMap) onboardingInput.getEmergencyContacts().get(i)).get("emergencyContactPhone"));
            c.setRelationship(""+((HashMap) onboardingInput.getEmergencyContacts().get(i)).get("emergencyContactRelastionship"));
            mycontacts.add(c);
            System.out.println(mycontacts);
        }
        cdi.saveAll(mycontacts);
        //assemble and save
//        cdi.saveAll( onboardingInput.getEmergencyContacts());
//        cdi.saveOrupdate(onboardingInput.get("referer"));

        //save to table PersonalDocument
        PersonalDocument personalDocument = new PersonalDocument();
        personalDocument.setEmployeeID(employeeID);
        personalDocument.setCreatedDate(UtilFunction.getTodayDate());
        personalDocument.setComment("WorkAuth");
        personalDocument.setPath(
               onboardingInput.getWorkAuthFile()
        );
        PersonalDocument avadar = new PersonalDocument();
        avadar.setEmployeeID(employeeID);
        avadar.setCreatedDate(UtilFunction.getTodayDate());
        avadar.setComment("avadar");
        avadar.setPath(
                onboardingInput.getWorkAuthFile()
        );


        //save workAuthDoc to s3
//        S3Controller s3 = new S3Controller();
//        s3.uploadToS3(userId, onboardingInputForm.workAuthDoc);

        // assign a not-fully-occupied-house (occupant<4) to this new user
//        HouseDaoImpl hdi = new HouseDaoImpl();
//        hdi.assignHouse(userId);
    }
}

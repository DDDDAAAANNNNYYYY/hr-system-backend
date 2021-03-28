package com.hr.hrserver.service;


import com.hr.hrserver.dao.*;
import com.hr.hrserver.pojo.Contact;
import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.PersonalDocument;
import com.hr.hrserver.util.UtilFunction;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
class OnboardingInputForm{
    int userId;
    String firstName;
    String lastName;
    String midName;
    String preferredName;
    String avatar;
    String ssn;
    Date dob;
    String gender;
    //contact info
    String address;
    String cellPhone;
    String workPhone;
    String email;
    //car & driver license info
    String carBrand;
    String carModel;
    String carColor;
    String driverLicenseNumber;
    Date driverLicenseExpirationDate;
    //migration info
    boolean isCitizen;
    File workAuthDoc;
    //contact & refer
    List<Contact> contactList;
    Contact referer;
}

public class onBoardingService {

    public void saveOnboardingData(Map<Object, Object> onboardingInput){

        //save to table employee
        Employee employee = new Employee();

        //find userID by user name
        UserDaoImpl userDao = new UserDaoImpl();
        int userId = userDao.findIdbyNmae((String) onboardingInput.get("username"));

        employee.setFirstName((String) onboardingInput.get("firstName"));
        employee.setLastName((String) onboardingInput.get("lastName"));
        employee.setMiddleName((String) onboardingInput.get("midName"));
        employee.setPreferredName((String) onboardingInput.get("preferredName"));

        //use default avatar
        employee.setAvatar("");
        //save avatar to s3
        //employee.setAvatar(onboardingInputForm.avatar);

        employee.setSSN((String) onboardingInput.get("ssn"));

        //parse date
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String sqlDate = sdf.format();
//        employee.setDOB(sqlDate);
        employee.setDOB((Date) onboardingInput.get("dob"));

        employee.setGender((String) onboardingInput.get("gender"));
        employee.setCellPhone((String) onboardingInput.get("cellPhone"));
        employee.setAlternatePhone((String) onboardingInput.get("workPhone"));
        employee.setEmail((String) onboardingInput.get("email"));
        employee.setAddress((String) onboardingInput.get("address"));

        employee.setStartDate(UtilFunction.getTodayDate());

        employee.setCar((String) onboardingInput.get("carBrand"));
        employee.setCarModel((String) onboardingInput.get("carModel"));
        employee.setCarColor((String) onboardingInput.get("carColor"));
        employee.setDriverLisence((String) onboardingInput.get("driverLicenseNumber"));

//        parse date
//        employee.setDriverLisence_ExpirationDate(
//         (Date) onboardingInput.get("driverLicenseExpirationDate")
//         );
        employee.setDOB((Date) onboardingInput.get("dob"));

        employee.setIsCitizen((boolean) onboardingInput.get("isCitizen")?1:0);

        EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
        int employeeID = (int) employeeDaoImpl.save(employee);

        //save to table contact
        ContactDaoImpl cdi = new ContactDaoImpl();

        //assemble and save
        cdi.saveAll((List) onboardingInput.get("contactList"));
//        cdi.saveOrupdate(onboardingInput.get("referer"));

        //save to table PersonalDocument
        PersonalDocument personalDocument = new PersonalDocument();
        personalDocument.setEmployeeID(employeeID);
        personalDocument.setCreatedDate(UtilFunction.getTodayDate());
        personalDocument.setComment("Work Authorization Document");
        personalDocument.setPath(
                userId+"/"+((File) onboardingInput.get("workAuthDoc")).getName()
        );
        PersonalDocumentDaoImpl pddi = new PersonalDocumentDaoImpl();
        pddi.save(personalDocument);

        //save workAuthDoc to s3
//        S3Controller s3 = new S3Controller();
//        s3.uploadToS3(userId, onboardingInputForm.workAuthDoc);

        // assign a not-fully-occupied-house (occupant<4) to this new user
        HouseDaoImpl hdi = new HouseDaoImpl();
        hdi.assignHouse(userId);
    }
}

package com.hr.hrserver.service;


import com.hr.hrserver.dao.*;
import com.hr.hrserver.model.OnboardingForm;
import com.hr.hrserver.pojo.*;
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
        //save reference data
        if (onboardingInput.getReferred().equals("True")){
            Contact reference = new Contact();
            reference.setContactsName(
                    onboardingInput.getReferFirstName()+ " " +
                            onboardingInput.getReferMiddleName()+ " " +
                            onboardingInput.getReferLastName()
            );
            reference.setIsReference(1);
            reference.setRelationship(onboardingInput.getReferRelationship());
            reference.setPhone(onboardingInput.getCellPhone());
            reference.setContactEmail(onboardingInput.getEmail());
            cdi.save(reference);
        }

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
            c.setIsEmergency(1);
            mycontacts.add(c);
            System.out.println(mycontacts);
        }
        cdi.saveAll(mycontacts);
        //assemble and save
//        cdi.saveAll( onboardingInput.getEmergencyContacts());
//        cdi.saveOrupdate(onboardingInput.get("referer"));
        Date d = new Date();
        ApplicationWorkFlow ap = new ApplicationWorkFlow();
        ap.setEmployeeID(employeeID);
        ap.setCreatedDate(d);

        ap.setStatus("Inprogress");
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

    private final EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    private final ApplicationWorkFlowDaoImpl workFlowDao = new ApplicationWorkFlowDaoImpl();
    private final VisaStatusDao visaDao = new VisaStatusDao();
    private final ContactDaoImpl contactDao = new ContactDaoImpl();
    private final PersonalDocumentDaoImpl documentDao = new PersonalDocumentDaoImpl();

    public List<EmployeeBasicInfo> getAllEmployeeBasicInfo(){
        List<EmployeeBasicInfo> allEmpBasicInfo = new ArrayList<>();
        List<Employee> allEmployee = employeeDao.getAllEmployee();
        for(Employee e : allEmployee){
            EmployeeBasicInfo EBI = new EmployeeBasicInfo(
                    e.getFirstName()+" "+e.getMiddleName()+" "+e.getLastName(),
                    e.getCellPhone().equals("")? e.getAlternatePhone() : e.getCellPhone(),
                    e.getEmail()
            );
            allEmpBasicInfo.add(EBI);
        }
        return allEmpBasicInfo;
    }

    // status as in form ApplicationWorkFlow
    public List<OnboardingForm> getAllOnboardingInfoByStatus(String status){
        List<OnboardingForm> output = new ArrayList<>();

        //get list of employeeId in ApplicationWorkFlow by status
        List<Integer> eidList = workFlowDao.getEidByAWFStatus(status);

        //if no eid found then no employee in given status
        if (eidList==null){
            System.out.println("No employee in "+status+" status");
            return null;
        }
        //else for each employee, assemble her onboarding info
        else{
            for(int eid : eidList){
                OnboardingForm ObForm = new OnboardingForm();

                Employee e = (Employee) employeeDao.get(eid);
                ObForm.setFirstName(e.getFirstName());
                ObForm.setLastName(e.getLastName());
                ObForm.setMiddleName(e.getMiddleName());
                ObForm.setPreferredName(e.getPreferredName());

                ObForm.setAvatar(e.getAvatar());

                ObForm.setAddress(e.getAddress());
                ObForm.setCellPhone(e.getCellPhone());
                ObForm.setWorkPhone(e.getAlternatePhone());
                ObForm.setEmail(e.getEmail());

                ObForm.setBirthday(e.getDOB());
                ObForm.setGender(e.getGender());

                ObForm.setSSN(e.getSSN());
                ObForm.setIsCitizenOrPerm(e.getIsCitizen()==1? "Yes" : "No");
                //ObForm.setGreenCardOrCitizen(); no such data in database

                VisaStatus visaStatus = visaDao.getNewestValidVisaByEid(eid);
                ObForm.setWorkAuth(visaStatus.getVisaType());
                //ObForm.setWorkAuthOther();
                ObForm.setWorkAuthOtherStart(visaStatus.getVisaStartDate().toString());
                ObForm.setWorkAuthOtherEnd(visaStatus.getVisaEndDate().toString());

                //get from PersonalDocument table, by eid, comment="WorkAuth"
                ObForm.setWorkAuthFile(documentDao.getNewestDocPathByEIDAndComment(eid,"WorkAuth"));

                //ObForm.setHasDriverLicense(e.getDriverLisence());
                ObForm.setDriverLicenseNumber(e.getDriverLisence());
                ObForm.setDriverLicenseExpirationDate(e.getDriverLisence_ExpirationDate());

                //find the referrence by eid (from contact table)
                Contact reference = contactDao.getReferenceByEid(eid);
                ObForm.setReferred(reference==null? "No":"Yes");
                //Do following only when isReferred
                if (ObForm.getReferred()=="Yes"){
                    //parse first, last, middle name
                    String[] nameArray = reference.getContactsName().split(" ");
                    if (nameArray.length == 3) {
                        ObForm.setReferFirstName(nameArray[0]);
                        ObForm.setReferLastName(nameArray[1]);
                        ObForm.setReferMiddleName(nameArray[2]);
                    } else if (nameArray.length == 2) {
                        ObForm.setReferFirstName(nameArray[0]);
                        ObForm.setReferLastName(nameArray[1]);
                        ObForm.setReferMiddleName("");
                    } else if (nameArray.length == 1) {
                        ObForm.setReferFirstName(nameArray[0]);
                        ObForm.setReferLastName("");
                        ObForm.setReferMiddleName("");
                    } else {
                        ObForm.setReferFirstName("Read");
                        ObForm.setReferLastName("Name");
                        ObForm.setReferMiddleName("Error");
                    }
                    ObForm.setReferPhone(reference.getPhone());
                    //ObForm.setReferAddress(); no such thing in database
                    ObForm.setReferEmail(reference.getContactEmail());
                    ObForm.setReferRelationship(reference.getRelationship());
                }

                List<Contact> emergencyContactList = new ArrayList<>();
                ObForm.setEmergencyContacts(
                        contactDao.getEmergContactByEid(eid)
                );

                output.add(ObForm);
            }
        }
        return output;
    }
}

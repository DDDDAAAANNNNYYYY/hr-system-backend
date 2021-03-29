package com.hr.hrserver.model;

import java.io.File;
import java.util.Date;
import java.util.List;

public class OnboardingForm {
    @Override
    public String toString() {
        return "OnboardingForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", preferredName='" + preferredName + '\'' +

                ", address='" + address + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", email='" + email + '\'' +
                ", SSN='" + SSN + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", isCitizenOrPerm='" + isCitizenOrPerm + '\'' +
                ", greenCardOrCitizen='" + greenCardOrCitizen + '\'' +
                ", workAuth='" + workAuth + '\'' +
                ", workAuthOther='" + workAuthOther + '\'' +
                ", workAuthOtherStart='" + workAuthOtherStart + '\'' +
                ", workAuthOtherEnd='" + workAuthOtherEnd + '\'' +
                ", workAuthFile='" + workAuthFile + '\'' +
                ", hasDriverLicense='" + hasDriverLicense + '\'' +
                ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
                ", driverLicenseExpirationDate=" + driverLicenseExpirationDate +

                ", referred='" + referred + '\'' +
                ", referFirstName='" + referFirstName + '\'' +
                ", referLastName='" + referLastName + '\'' +
                ", referMiddleName='" + referMiddleName + '\'' +
                ", referPhone='" + referPhone + '\'' +
                ", referAddress='" + referAddress + '\'' +
                ", referEmail='" + referEmail + '\'' +
                ", referRelationship='" + referRelationship + '\'' +
                ", emergencyContacts=" + emergencyContacts +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsCitizenOrPerm() {
        return isCitizenOrPerm;
    }

    public void setIsCitizenOrPerm(String isCitizenOrPerm) {
        this.isCitizenOrPerm = isCitizenOrPerm;
    }

    public String getGreenCardOrCitizen() {
        return greenCardOrCitizen;
    }

    public void setGreenCardOrCitizen(String greenCardOrCitizen) {
        this.greenCardOrCitizen = greenCardOrCitizen;
    }

    public String getWorkAuth() {
        return workAuth;
    }

    public void setWorkAuth(String workAuth) {
        this.workAuth = workAuth;
    }

    public String getWorkAuthOther() {
        return workAuthOther;
    }

    public void setWorkAuthOther(String workAuthOther) {
        this.workAuthOther = workAuthOther;
    }

    public String getWorkAuthOtherStart() {
        return workAuthOtherStart;
    }

    public void setWorkAuthOtherStart(String workAuthOtherStart) {
        this.workAuthOtherStart = workAuthOtherStart;
    }

    public String getWorkAuthOtherEnd() {
        return workAuthOtherEnd;
    }

    public void setWorkAuthOtherEnd(String workAuthOtherEnd) {
        this.workAuthOtherEnd = workAuthOtherEnd;
    }

    public String getWorkAuthFile() {
        return workAuthFile;
    }

    public void setWorkAuthFile(String workAuthFile) {
        this.workAuthFile = workAuthFile;
    }

    public String getHasDriverLicense() {
        return hasDriverLicense;
    }

    public void setHasDriverLicense(String hasDriverLicense) {
        this.hasDriverLicense = hasDriverLicense;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public Date getDriverLicenseExpirationDate() {
        return driverLicenseExpirationDate;
    }

    public void setDriverLicenseExpirationDate(Date driverLicenseExpirationDate) {
        this.driverLicenseExpirationDate = driverLicenseExpirationDate;
    }



    public String getReferred() {
        return referred;
    }

    public void setReferred(String referred) {
        this.referred = referred;
    }

    public String getReferFirstName() {
        return referFirstName;
    }

    public void setReferFirstName(String referFirstName) {
        this.referFirstName = referFirstName;
    }

    public String getReferLastName() {
        return referLastName;
    }

    public void setReferLastName(String referLastName) {
        this.referLastName = referLastName;
    }

    public String getReferMiddleName() {
        return referMiddleName;
    }

    public void setReferMiddleName(String referMiddleName) {
        this.referMiddleName = referMiddleName;
    }

    public String getReferPhone() {
        return referPhone;
    }

    public void setReferPhone(String referPhone) {
        this.referPhone = referPhone;
    }

    public String getReferAddress() {
        return referAddress;
    }

    public void setReferAddress(String referAddress) {
        this.referAddress = referAddress;
    }

    public String getReferEmail() {
        return referEmail;
    }

    public void setReferEmail(String referEmail) {
        this.referEmail = referEmail;
    }

    public String getReferRelationship() {
        return referRelationship;
    }

    public void setReferRelationship(String referRelationship) {
        this.referRelationship = referRelationship;
    }

    public List getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    private String firstName;
    private String lastName;
    private String middleName;
    private String preferredName;

    private String avatar;

    private String address;
    private String cellPhone;
    private String workPhone;
    private String email;
    private String SSN;
    private Date birthday;
    private String gender;
    private String isCitizenOrPerm;
    private String greenCardOrCitizen;
    private String workAuth;
    private String workAuthOther;
    private String workAuthOtherStart;
    private String workAuthOtherEnd;
    private String workAuthFile;
    private String hasDriverLicense;
    private String driverLicenseNumber;
    private Date driverLicenseExpirationDate;

    private String referred;
    private String referFirstName;
    private String referLastName;
    private String referMiddleName;
    private String referPhone;
    private String referAddress;
    private String referEmail;
    private String referRelationship;
    private List emergencyContacts;




//    lastName: string;
//    middleName: string;
//    preferredName: string;
//    avatar: File;
//    address: string;
//    cellPhone: string;
//    workPhone: string;
//    email: string;
//    SSN: string;
//    birthday: Date;
//    gender: string;
//    isCitizenOrPerm: string;
//    greenCardOrCitizen: string;
//    workAuth: string;
//    workAuthOther: string;
//    workAuthOtherStart: Date;
//    workAuthOtherEnd: Date;
//    workAuthFile: File;
//    hasDriverLicense: string;
//    driverLicenseNumber: string;
//    driverLicenseExpirationDate: Date;
//    driverLicenseCopy: File;
//    referred: string;
//    referFirstName: string;
//    referLastName: string;
//    referMiddleName: string;
//    referPhone: number;
//    referAddress: string;
//    referEmail: string;
//    referRelationship: string;
//    emergencyContacts: FormArray;
}


package com.hr.hrserver.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Employee")
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int ID;
    @Column(name="UserID")
    int UserID;
    @Column(name="FirstName")
    String FirstName;
    @Column(name="LastName")
    String LastName;
    @Column(name="MiddleName")
    String MiddleName;
    @Column(name="PreferredName")
    String PreferredName;
    @Column(name="Email")
    String Email;
    @Column(name="CellPhone")
    String CellPhone;
    @Column(name="AlternatePhone")
    String AlternatePhone;
    @Column(name="Gender")
    String Gender;
    @Column(name="SSN")
    String SSN;
    @Column(name="DOB")
    Date DOB;
    @Column(name="Title")
    String Title;
    @Column(name="ManagerID")
    int ManagerID;
    @Column(name="StartDate")
    Date StartDate;
    @Column(name="EndDate")
    Date EndDate;
    @Column(name="Avatar")
    String Avatar;
    @Column(name="Car")
    String Car;
    @Column(name="CarModel")
    String CarModel;
    @Column(name="CarColor")
    String CarColor;
    @Column(name="DriverLisence")
    String DriverLisence;
    @Column(name="DriverLisence_ExpirationDate")
    Date DriverLisence_ExpirationDate;
    @Column(name="HouseID")
    int HouseID;
    @Column(name="Address")
    String Address;
    @Column(name="isCitizen")
    int isCitizen;
//    @OneToMany(mappedBy = "EmployeeID"
//    )
//    List<Contact> contactList;
}
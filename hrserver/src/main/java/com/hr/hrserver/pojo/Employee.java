package com.hr.hrserver.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="Employee")
public class Employee {
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
    @Column(name="DriverLisence")
    String DriverLisence;
    @Column(name="DriverLisence_ExpirationDate")
    Date DriverLisence_ExpirationDate;
    @Column(name="HouseID")
    int HouseID;
}

package com.hr.hrserver.pojo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name="Contact")
public class Contact {
    @Id
    int ID;
    @Column(name="EmployeeID")
    int EmployeeID;
    @Column(name="Relationship")
    String Relationship;
    @Column(name="Title")
    String Title;
    @Column(name="isReference")
    int isReference;
    @Column(name="isEmergency")
    int isEmergency;
    @Column(name="isLandlord")
    int isLandlord;
}

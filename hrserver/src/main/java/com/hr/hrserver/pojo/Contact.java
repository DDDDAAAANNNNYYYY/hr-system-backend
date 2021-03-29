package com.hr.hrserver.pojo;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Setter
@Getter
@Data
@Entity
@Table(name="Contact")
public class Contact {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name="Name")
    String contactsName;
    @Column(name="cellPhone")
    String phone;
    @Column(name="Email")
    String contactEmail;
//    @ManyToOne
//    @JoinColumn(name = "EmployeeID")
//    Employee e;
}

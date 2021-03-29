package com.hr.hrserver.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString

@Table(name = "FacilityReport")
public class FacilityReport {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="ID")
    int ID;
    @Column(name="EmployeeID")
    int EmployeeID;
    @Column(name="ReportDate")
    Date ReportDate;
    @Column(name="Title")
    String Title;
    @Column(name="Description")
    String Description;
    @Column(name="Status")
    String Status;

}

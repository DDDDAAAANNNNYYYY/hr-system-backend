package com.hr.hrserver.pojo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="FacilityReportDetail")
public class FacilityReportDetail {
    @Id
    @Column(name="ID")
    int ID;
    @Column(name="ReportID")
    int ReportID;
    @Column(name="EmployeeID")
    int EmployeeID;
    @Column(name="Comments")
    String Comment;
    @Column(name="CreatedDate")
    Date CreatedDate;
    @Column(name="LastModificationDate")
    Date LastModificationDate;

}
package com.hr.hrserver.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name="VisaStatus")
public class VisaStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;
    @Column(name="EmployeeID")
    int EmployeeID;
    @Column(name="VisaType")
    String VisaType;
    @Column(name="Active")
    int Active;
    @Column(name="ModificationDate")
    Date ModifiecationDate;
    @Column(name="VisaStartDate")
    Date VisaStartDate;
    @Column(name="VisaEndDate")
    Date VisaEndDate;

    @Override
    public String toString() {
        return "VisaStatus{" +
                "ID=" + ID +
                ", EmployeeID=" + EmployeeID +
                ", VisaType='" + VisaType + '\'' +
                ", Active=" + Active +
                ", ModifiecationDate=" + ModifiecationDate +
                ", VisaStartDate=" + VisaStartDate +
                ", VisaEndDate=" + VisaEndDate +
                '}';
    }
}

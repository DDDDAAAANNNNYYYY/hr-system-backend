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
@Table(name="ApplicationWorkFlow")
public class ApplicationWorkFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;
    @Column(name="EmployeeID")
    int EmployeeID;
    @Column(name="CreatedDate")
    Date CreatedDate;
    @Column(name="ModificationDate")
    String ModificationDate;
    @Column(name="Status")
    String Status;
    @Column(name="Comments")
    String Comments;
}

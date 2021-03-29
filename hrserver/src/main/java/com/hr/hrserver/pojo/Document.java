package com.hr.hrserver.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Data
@Entity
@Table(name="PersonalDocument")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;
    @Column(name="EmployeeID")
    int EmployeeID;
    @Column(name="Path")
    String path;
    @Column(name="Title")
    String Title;
    @Column(name="Comment")
    String Comment;
    @Column(name="CreatedDate")
    Date CreatedDate;

}

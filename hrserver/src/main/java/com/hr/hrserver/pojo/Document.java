package com.hr.hrserver.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Data
@Entity
@Table(name="PersonalDocument")
public class Document {
    @Id
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

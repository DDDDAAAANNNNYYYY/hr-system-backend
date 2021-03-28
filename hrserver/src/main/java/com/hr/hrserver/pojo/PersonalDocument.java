package com.hr.hrserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="PersonalDocument")
public class PersonalDocument {
    @Id
    @Column(name="ID")
    public int ID;
    @Column(name="EmployeeID")
    public int EmployeeID;
    @Column(name="Path")
    public String Path;
    @Column(name="Title")
    public String Title;
    @Column(name="Comment")
    public String Comment;
    @Column(name="CreatedDate")
    public Date CreatedDate;

}

package com.hr.hrserver.pojo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="RegistrationToken")
public class RegistrationToken {
    @Id
    @Column(name="ID")
    public String ID;
    @Column(name="Token")
    public String Token;
    @Column(name="ValidDuration")
    public final static int VALIDDURATION = 60*3;
    @Column(name="Email")
    public String email;
    @Column(name="CreatedBy")
    public Date CreateBy;

}

package com.hr.hrserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationToken {

    public String ID;
    public String Token;
    public final static int VALIDDURATION = 60*3;
    public String email;
    public Date CreateBy;

}

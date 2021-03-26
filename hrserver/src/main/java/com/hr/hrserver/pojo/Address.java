package com.hr.hrserver.pojo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Address")
public class Address {
    @Id
    int ID;
    @Column(name="EmployeeID")
    int EmployeeID;
    @Column(name="AddressLine1")
    String AddressLine1;
    @Column(name="AddressLine2")
    String AddressLine2;
    @Column(name="City")
    String City;
    @Column(name="Zipcode")
    String Zipcode;
    @Column(name="StateName")
    String StateName;
    @Column(name="StateAbbr")
    String StateAbbr;
}

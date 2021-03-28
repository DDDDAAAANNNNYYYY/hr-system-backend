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

@Table(name = "House")
public class House {
    @Id
    @Column(name="ID")
    int ID;
    @Column(name="ContactID")
    int ContactID;
    @Column(name="Address")
    int AddressID;
    @Column(name="NumberOfPerson")
    int NumberOfPerson;
}

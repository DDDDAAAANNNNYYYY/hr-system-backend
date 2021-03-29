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

@Table(name = "House")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    int ID;
    @Column(name="ContactID")
    int ContactID;
    @Column(name="Address")
    int AddressID;
    @Column(name="NumberOfPerson")
    int NumberOfPerson;
}

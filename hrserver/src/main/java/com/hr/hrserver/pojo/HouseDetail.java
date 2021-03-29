package com.hr.hrserver.pojo;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HouseDetail {
    public Address address;
    public List<Occupant> occupantList;
}

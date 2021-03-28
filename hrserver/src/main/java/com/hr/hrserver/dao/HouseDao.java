package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.House;

public interface HouseDao {
    public House getHouseByID(int hid);
    public int getHouseIDWhichNumOfPeopLessThan(int num_of_people);
    public int increaseNumOfPeopleByHouseID(int houseID); //return the new number of people
}


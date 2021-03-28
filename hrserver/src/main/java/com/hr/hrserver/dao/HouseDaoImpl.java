package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.House;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

public class HouseDaoImpl extends BaseDaoImpl implements HouseDao{

    private int MaxPeoplePerHouse = 4;
    public HouseDaoImpl() {
        super(House.class);
    }

    @Override
    public House getHouseByID(int hid) {
        Query query = getCurrentSession().createQuery("from House h where h.ID=:hid");
        query.setParameter("hid", hid);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return (House) query.list().get(0);
    }

    @Override
    public int getHouseIDWhichNumOfPeopLessThan(int num_of_people) {
        String sql = "select ID from House where NumberOfPerson<"+num_of_people+" order by ID";
        Query query = getCurrentSession().createSQLQuery(sql);
        if(CollectionUtils.isEmpty(query.list())) {
            return -1;
        }
        return (int) query.list().get(0);
    }

    @Override
    public int increaseNumOfPeopleByHouseID(int houseID) {
        Query query = getCurrentSession().createQuery("from House h where h.ID=:hid");
        query.setParameter("hid", houseID);
        if(CollectionUtils.isEmpty(query.list())) {
            return -1;
        }
        House h = (House) query.list().get(0);
        int num_of_op = h.getNumberOfPerson()+1;
        h.setNumberOfPerson(num_of_op);
        this.saveOrupdate(h);
        return num_of_op;
    }

    // assign a not-fully-occupied-house (occupant<4) to a new user, and return the houseID
    public int assignHouse(int userID){
        int houseId = this.getHouseIDWhichNumOfPeopLessThan(MaxPeoplePerHouse);
        increaseNumOfPeopleByHouseID(houseId);
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = employeeDao.getEmployeeByUserId(userID);
        employee.setHouseID(houseId);
        employeeDao.saveOrupdate(employee.getID());
        return houseId;
    }

}

